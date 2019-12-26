//package cz.cvut.fel.ear.semestralka.web.controller;
//
//import cz.cvut.fel.ear.semestralka.domain.MailObject;
//import cz.cvut.fel.ear.semestralka.service.EmailServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.validation.Valid;
//
//@Controller
//public class EmailController {
//    private final EmailServiceImpl emailService;
//    private final SimpleMailMessage template;
//    @Autowired
//    public EmailController(EmailServiceImpl emailService, SimpleMailMessage template) {
//        this.emailService = emailService;
//        this.template = template;
//    }
//
//    @RequestMapping(value = "/mail/send", method = RequestMethod.POST)
//    public String createMail(Model model,
//                             @ModelAttribute("mailObject") @Valid MailObject mailObject,
//                             Errors errors) {
//        if (errors.hasErrors()) {
//            return "mail/send";
//        }
//        emailService.sendSimpleMessage(mailObject.getTo(),
//                mailObject.getSubject(), mailObject.getText());
//
//        return "redirect:/dashboard";
//    }
//}
