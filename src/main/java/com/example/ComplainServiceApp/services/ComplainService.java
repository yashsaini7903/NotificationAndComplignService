package com.example.ComplainServiceApp.services;

import com.example.ComplainServiceApp.model.CompainCreatedEvent;
import com.example.ComplainServiceApp.entity.Complain;
import com.example.ComplainServiceApp.repositry.ComplainRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplainService {

    @Autowired
    private ComplainRepositry complainRepositry;

    @Autowired
    private ComplianProducerService complianProducerService;

    public Complain saveComplainToprocess(Complain complain){
        complain.setType("OTHER");
       Complain saved = complainRepositry.save(complain);
        CompainCreatedEvent event = new CompainCreatedEvent();
        event.setId(saved.getId());
        event.setUsername(saved.getUsername());
        event.setTitle(saved.getTitle());
        event.setComplain(saved.getComplain());
        complianProducerService.complainProduceEvent(event);
       return saved;
    }
    public Complain saveCompalin(Complain complain){
       return complainRepositry.save(complain);
    }
}
