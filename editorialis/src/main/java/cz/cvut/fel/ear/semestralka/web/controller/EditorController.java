package cz.cvut.fel.ear.semestralka.web.controller;

import cz.cvut.fel.ear.semestralka.service.EditorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/editor/")
public class EditorController {

    private final EditorServiceImpl edServ;
    @Autowired
    public EditorController(EditorServiceImpl edServ) {
        this.edServ = edServ;
    }


}
