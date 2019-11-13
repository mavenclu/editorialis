//package cz.cvut.fel.ear.web.controller;
//
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.services.gmail.Gmail;
//import cz.cvut.fel.ear.service.GmailService;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import java.io.IOException;
//import java.security.GeneralSecurityException;
//
//@RestController
//public class GmailController {
//
//    @RequestMapping("/gmail")
//    public void sendEmail() throws MessagingException, IOException, GeneralSecurityException {
//        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//        Gmail service = new Gmail.Builder(HTTP_TRANSPORT, GmailService.getJsonFactory(), GmailService.getCredentials(HTTP_TRANSPORT))
//                .setApplicationName(GmailService.getApplicationName())
//                .build();
//
//        MimeMessage message = GmailService.createEmail("miyaquey@gmail.com", "me", "Test mail from editorialis", "Hello, dear reviewer. A new manuscript has been assigned to you. Please, look at it.");
//        GmailService.sendMessage(service, "me", message);
//    }
//}
