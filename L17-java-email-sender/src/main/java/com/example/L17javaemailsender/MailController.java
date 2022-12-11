package com.example.L17javaemailsender;


import com.fasterxml.jackson.databind.ext.Java7HandlersImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
public class MailController {

    private Logger LOGGER = LoggerFactory.getLogger(MailController.class);

    @Autowired
    JavaMailSender javaMailSender;


    @PostMapping("/sendEmail")
    ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom("5pointmentor@gmail.com");
        simpleMailMessage.setSubject(emailRequest.getSubject());
        simpleMailMessage.setTo(emailRequest.getToEmail());
        simpleMailMessage.setText(emailRequest.getBody());
        simpleMailMessage.setCc(emailRequest.getCc());


        javaMailSender.send(simpleMailMessage);

        return ResponseEntity.ok("Sent Successfully");

    }

}
