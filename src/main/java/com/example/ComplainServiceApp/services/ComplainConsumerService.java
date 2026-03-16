package com.example.ComplainServiceApp.services;

import com.example.ComplainServiceApp.model.CompainCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ComplainConsumerService {
   @Autowired
   private AiProcessesService aiProcessesService;

   @KafkaListener(topics = "complain.created",groupId = "complian-group")
   public void ComplainProcessor(CompainCreatedEvent event){
      log.info("Received complaint event from Kafka: {}", event);
      aiProcessesService.processComplian(event);
   }
}
