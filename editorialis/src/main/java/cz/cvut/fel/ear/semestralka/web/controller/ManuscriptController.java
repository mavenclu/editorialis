package cz.cvut.fel.ear.semestralka.web.controller;

import cz.cvut.fel.ear.semestralka.dao.ManuscriptRepository;
import cz.cvut.fel.ear.semestralka.dao.ReviewerRepository;
import cz.cvut.fel.ear.semestralka.domain.Manuscript;
import cz.cvut.fel.ear.semestralka.domain.ManuscriptState;
import cz.cvut.fel.ear.semestralka.service.EmailService;
import cz.cvut.fel.ear.semestralka.service.ManuscriptService;
import cz.cvut.fel.ear.semestralka.service.ManuscriptStateMachineService;
import cz.cvut.fel.ear.semestralka.service.ReviewerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "*")
@RepositoryRestController
@RequestMapping("/manuscripts")
public class ManuscriptController {
    private final Logger log = LoggerFactory.getLogger(ManuscriptController.class);


    private final ManuscriptRepository manRepo;
    private final ManuscriptService manServ;
    private final ReviewerService reviewerServ;
    private final ManuscriptStateMachineService machineServ;
    private final ReviewerRepository reviewerRepo;
    private final EmailService emailService;

    @Autowired
    public ManuscriptController(ManuscriptRepository manRepo, ManuscriptService manServ, ReviewerService reviewerServ, ManuscriptStateMachineService machineServ, ReviewerRepository reviewerRepo, EmailService emailService) {
        this.manRepo = manRepo;
        this.manServ = manServ;
        this.reviewerServ = reviewerServ;
        this.machineServ = machineServ;
        this.reviewerRepo = reviewerRepo;
        this.emailService = emailService;
    }

    @Transactional
    @PatchMapping("/{id}/accept")
    @ResponseBody
    ResponseEntity<Manuscript> accept(@PathVariable Long id) {
        if (manRepo.existsById(id)) {
            Manuscript manuscript = manServ.findById(id);
            machineServ.acceptManuscript(manuscript.getManuscriptId());
            manuscript.getEventsSequence().setWhenAccepted(LocalDateTime.now());
            manuscript.setClosed(true);
            manRepo.save(manuscript);


            EntityModel<Manuscript> model = EntityModel.of(manuscript);
            model.add(linkTo(methodOn(ManuscriptController.class).accept(id)).withSelfRel());
            return new ResponseEntity<>(manuscript, HttpStatus.OK);

        } else {
            log.error("trying to approve empty manuscript");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//            throw new IllegalArgumentException("Could not approve manuscript with id: " + id);
        }


    }

    ;

    @Transactional
    @PatchMapping("/{id}/reject")
    @ResponseBody
    ResponseEntity<Manuscript> reject(@PathVariable Long id) {
        if (manRepo.existsById(id)) {
            Manuscript manuscript = manServ.findById(id);
//            machineServ.rejectManuscript(manuscript.getManuscriptId());
            manuscript.getEventsSequence().setWhenRejected(LocalDateTime.now());
            manuscript.setManuscriptStatus(ManuscriptState.REJECTED);

//            manuscript.getAuthors().forEach(new Consumer<Author>() {
//                @Override
//                public void accept(Author author) {
//                    emailService.sendSimpleMessage(author.getEmail(), "Manuscript Rejected", "With great regret we would like to inform you that your manuscript: " + manuscript.getTitle() +
//                            " submitted " + manuscript.getEventsSequence().getWhenUploaded() +
//                            " to editorialis was rejected. Yours sincerelly, editors team.");
//                }
//            });


            emailService.sendSimpleMessage("miyaquey@gmail.com", "Manuscript Rejected", "With great regret we would like to inform you that your manuscript: " + manuscript.getTitle() +
                    " submitted " + manuscript.getEventsSequence().getWhenUploaded() +
                    " to editorialis was rejected. Yours sincerelly, editors team.");

            manuscript.setClosed(true);
            manRepo.save(manuscript);
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
    @ResponseBody
    ResponseEntity<Manuscript> assignToReviewer(@PathVariable Long id, @RequestParam(name = "reviewerId", required = true) Long reviewerId) {
        if (manRepo.existsById(id) && reviewerRepo.existsById(reviewerId)) {
            Manuscript manuscript = manServ.findById(id);
//            machineServ.assignToReviewer(manuscript.getManuscriptId(), reviewerId);
            manuscript.getEventsSequence().setWhenAssignedToReviewer(LocalDateTime.now());
            manuscript.setReviewer(reviewerServ.findById(reviewerId));
            manuscript.setManuscriptStatus(ManuscriptState.PEER_REVIEW);
            manRepo.save(manuscript);
//            manuscript.getAuthors().forEach(new Consumer<Author>() {
//                @Override
//                public void accept(Author author) {
//                    emailService.sendSimpleMessage(author.getEmail(), "Manuscript assgined to reviewer", "We would like to inform you that your manuscript: " + manuscript.getTitle() +
//                            " submitted " + manuscript.getEventsSequence().getWhenUploaded() +
//                            " to editorialis was assigned for review. \nSincerely yours, editors team.");
//                }
//            });

            emailService.sendSimpleMessage("miyaquey@gmail.com", "TO AUTHORS. Manuscript assgined to reviewer", "We would like to inform you that your manuscript: " + manuscript.getTitle() +
                    " submitted " + manuscript.getEventsSequence().getWhenUploaded() +
                    " to editorialis was assigned for review. \nSincerely yours, editors team.");

            emailService.sendSimpleMessage("miyaquey@gmail.com", "TO REVIEWER. New Manuscript for review",
                    "You have been assigned with a new manuscript: " + manuscript.getTitle() +
                            " \nSincerely yours, editorialis team.");

//            emailService.sendSimpleMessage(reviewerServ.findById(reviewerId).getEmail(), "New Manuscript for review",
//                    "You have been assigned with a new manuscript: " + manuscript.getTitle() +
//                    " \nSincerely yours, editorialis team.");
            EntityModel<Manuscript> model = EntityModel.of(manuscript);
            model.add(linkTo(methodOn(ManuscriptController.class).assignToReviewer(id, reviewerId)).withSelfRel());
            return new ResponseEntity<>(manuscript, HttpStatus.OK);

        } else {
            if (!manRepo.existsById(id)) {
                log.error("trying to assign to a reviewer an empty manuscript.");
            } else if (!reviewerRepo.existsById(reviewerId)) {
                log.error("trying to assign a mansucript to an empty reviewer .");
            } else {
                log.error("no manuscript with id : " + id + " and reviewer with id: " + reviewerId);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//            throw new IllegalArgumentException("Could not reject manuscript with id: " + id);
        }
    }


}
