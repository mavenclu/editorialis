package cz.cvut.fel.ear.semestralka.web.controller;

import cz.cvut.fel.ear.semestralka.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EmailController {
    private final EmailServiceImpl emailService;
    @Autowired
    public EmailController(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }



    @RequestMapping(value = "/mail/send", method = RequestMethod.GET)
    public String sendEmail(){
        emailService.sendSimpleMessage("miyaquey@gmail.com", "Test email", "New manuscript");
        return "redirect:/editor";
    }
}
