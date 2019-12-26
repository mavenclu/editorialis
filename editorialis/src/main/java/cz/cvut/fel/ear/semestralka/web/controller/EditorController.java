package cz.cvut.fel.ear.semestralka.web.controller;

import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class EditorController {

    @ExceptionHandler(NotFoundException.class)
    public String notFount(Model model, Exception ex){
        model.addAttribute("exception", ex);
        return "error";
    }
}
