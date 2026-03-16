package com.example.ComplainServiceApp.services;

import com.example.ComplainServiceApp.model.MailEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailConsumerService{

    @Autowired
    private MailSenderServive mailSenderServive;

    @KafkaListener(topics = "mail.created", groupId = "mail_group")
    public void mailConsumer(MailEvent mailEvent){
        try{
            mailSenderServive.sendMail(mailEvent);
            log.info("mail reviced in consumer");
        } catch (Exception e) {
            log.error("error in mail consumer",e);
        }
    }
}
