package com.example.ComplainServiceApp.services;

import com.example.ComplainServiceApp.model.MailEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailProducerService{

    private final KafkaTemplate<String, MailEvent> kafkaTemplate;
    private final String topic = "mail.created";

    public void mailProducer(String mail, String subject , String body){
        try{
            MailEvent mailEvent = new MailEvent(mail,subject,body);
            kafkaTemplate.send(topic, mailEvent);
            log.info("mail enter in producer");
        } catch (Exception e) {
            log.error("mail producer error",e);
        }
    }
}
