package cz.cvut.fel.ear.semestralka.service;

public interface EmailService {
    void sendSimpleMessage(String to,
                           String subject,
                           String text);

    //    void sendSimpleMessageUsingTemplate(String to,
//                                        String subject,
//                                        SimpleMailMessage template,
//                                        String ...templateArgs);
    void sendMessageWithAttachment(String to,
                                   String subject,
                                   String text,
                                   String pathToAttachment);
}