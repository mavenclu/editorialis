package cz.cvut.fel.ear.semestralka.web.controller;

import cz.cvut.fel.ear.semestralka.dao.ManuscriptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ManuscriptController {
    @Autowired
    private ManuscriptRepository manRepo;



}
