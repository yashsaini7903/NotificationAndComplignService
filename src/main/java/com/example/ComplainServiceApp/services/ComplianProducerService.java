package com.example.ComplainServiceApp.services;

import com.example.ComplainServiceApp.model.CompainCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ComplianProducerService{

     private final KafkaTemplate<String, CompainCreatedEvent> kafkaTemplate;
     private static final String Topic = "complain.created";

     public void complainProduceEvent(CompainCreatedEvent compainCreatedEvent){
         try{ kafkaTemplate.send(Topic,compainCreatedEvent.getId(),compainCreatedEvent);
             log.info("kafka producer activate");
         } catch (Exception e) {
             log.error("error in complain producer",e);
         }

     }
}
