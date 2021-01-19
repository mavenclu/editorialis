package cz.cvut.fel.ear.semestralka.config;

import cz.cvut.fel.ear.semestralka.events.ManuscriptEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class AppConfig {
    @Bean
    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Manuscript assignment");
        message.setText(
                "\n%s\n You have been assigned with a new manuscript. Please check out your dashboard");
        return message;
    }

    @Bean
    public StateMachineLogListener stateMachineLogListener() {
        return new StateMachineLogListener();
    }


    @Bean
    ManuscriptEventHandler manuscriptEventHandler() {
        return new ManuscriptEventHandler();
    }
}
