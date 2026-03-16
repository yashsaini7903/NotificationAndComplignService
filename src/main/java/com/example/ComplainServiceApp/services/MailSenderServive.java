package com.example.ComplainServiceApp.services;

import com.example.ComplainServiceApp.model.MailEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class MailSenderServive{

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailProducerService mailProducerService;

    private ExecutorService executor = Executors.newFixedThreadPool(10);

    public void sendMail(MailEvent mailEvent){
        executor.submit(()->{
            try{
                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setTo(mailEvent.getMail());
                mail.setSubject(mailEvent.getSubject());
                mail.setText(mailEvent.getBody());
                javaMailSender.send(mail);
                log.info("mail sended successfully to"+mailEvent.getMail());
            } catch (Exception e) {
                //mailProducerService.mailProducer(mailEvent.getMail(), mailEvent.getSubject(), mailEvent.getBody());
                log.error("error occurred during sending mail");
            }
        });
    }
}
