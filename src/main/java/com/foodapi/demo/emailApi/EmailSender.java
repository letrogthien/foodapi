package com.foodapi.demo.emailApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String toEmail, String sub, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("letrogthien@gmail.com");
        message.setTo(toEmail);
        message.setSubject(sub);
        message.setText(body);

        javaMailSender.send(message);
    }
}
