package cz.cvut.fel.ear.semestralka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class AppConfig {
    @Bean
    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(
                "\n%s\n You have been assigned with new manuscript. Please check out your dashboard");
        return message;
    }
}
