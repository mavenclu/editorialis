package cz.cvut.fel.ear.semestralka.web.controller;

import cz.cvut.fel.ear.semestralka.dao.ManuscriptRepository;
import cz.cvut.fel.ear.semestralka.domain.Manuscript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ManuscriptController {
    @Autowired
    private ManuscriptRepository manuscriptRepo;

    @RequestMapping("/manuscripts")
    public Iterable<Manuscript> getManuscripts(){
        return manuscriptRepo.findAll();
    }

    

}
