package cz.cvut.fel.ear.semestralka.web.controller;

import cz.cvut.fel.ear.semestralka.dao.ManuscriptRepository;
import cz.cvut.fel.ear.semestralka.dao.ReviewerRepository;
import cz.cvut.fel.ear.semestralka.domain.Manuscript;
import cz.cvut.fel.ear.semestralka.service.ManuscriptService;
import cz.cvut.fel.ear.semestralka.service.ManuscriptStateMachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "*")
@RepositoryRestController
@RequestMapping("/manuscripts")
public class ManuscriptController {
    private final Logger log = LoggerFactory.getLogger(ManuscriptController.class);


    private final ManuscriptRepository manRepo;
    private final ManuscriptService manServ;
    private final ManuscriptStateMachineService machineServ;
    private final ReviewerRepository reviewerRepo;

    @Autowired
    public ManuscriptController(ManuscriptRepository manRepo, ManuscriptService manServ, ManuscriptStateMachineService machineServ, ReviewerRepository reviewerRepo) {
        this.manRepo = manRepo;
        this.manServ = manServ;
        this.machineServ = machineServ;
        this.reviewerRepo = reviewerRepo;
    }

    @Transactional
    @PatchMapping("/{id}/accept")
    @ResponseBody ResponseEntity<Manuscript> accept(@PathVariable Long id){
        if (manRepo.existsById(id)){
        Manuscript manuscript = manServ.findById(id);
        machineServ.acceptManuscript(manuscript.getManuscriptId());
        manuscript.getEventsSequence().setWhenAccepted(LocalDateTime.now());

            EntityModel<Manuscript> model = EntityModel.of(manuscript);
            model.add(linkTo(methodOn(ManuscriptController.class).accept(id)).withSelfRel());
            return new ResponseEntity<>(manuscript, HttpStatus.OK);

        }else {
            log.error("trying to approve empty manuscript");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//            throw new IllegalArgumentException("Could not approve manuscript with id: " + id);
        }


    };
    @Transactional
    @PatchMapping("/{id}/reject")
    @ResponseBody ResponseEntity<Manuscript> reject(@PathVariable Long id){
        if (manRepo.existsById(id)){
            Manuscript manuscript = manServ.findById(id);
            machineServ.rejectManuscript(manuscript.getManuscriptId());
            manuscript.getEventsSequence().setWhenRejected(LocalDateTime.now());

            EntityModel<Manuscript> model = EntityModel.of(manuscript);
            model.add(linkTo(methodOn(ManuscriptController.class).reject(id)).withSelfRel());
            return new ResponseEntity<>(manuscript, HttpStatus.OK);
        } else {
            log.error("trying to reject empty manuscript");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//            throw new IllegalArgumentException("Could not reject manuscript with id: " + id);
        }
    }
    @Transactional
    @PatchMapping("/{id}/assign-to-reviewer")
    @ResponseBody ResponseEntity<Manuscript> assignToReviewer(@PathVariable Long id, @RequestParam(name = "reviewerId", required = true) Long reviewerId){
        if (manRepo.existsById(id) && reviewerRepo.existsById(reviewerId)){
            Manuscript manuscript = manServ.findById(id);
            machineServ.assignToReviewer(manuscript.getManuscriptId(), reviewerId);
            manuscript.getEventsSequence().setWhenAssignedToReviewer(LocalDateTime.now());

            EntityModel<Manuscript> model = EntityModel.of(manuscript);
            model.add(linkTo(methodOn(ManuscriptController.class).assignToReviewer(id, reviewerId)).withSelfRel());
            return new ResponseEntity<>(manuscript, HttpStatus.OK);

        } else {
            if (!manRepo.existsById(id)){
                log.error("trying to assign to a reviewer an empty manuscript.");
            }else if (!reviewerRepo.existsById(reviewerId)){
                log.error("trying to assign a mansucript to an empty reviewer .");
            } else{
                log.error("no manuscript with id : " + id + " and reviewer with id: " + reviewerId);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//            throw new IllegalArgumentException("Could not reject manuscript with id: " + id);
        }
    }


}
