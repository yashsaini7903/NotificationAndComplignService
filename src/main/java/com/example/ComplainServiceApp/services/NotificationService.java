package com.example.ComplainServiceApp.services;

import com.example.ComplainServiceApp.entity.User;
import com.example.ComplainServiceApp.repositry.UserRepositry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@Slf4j
public class NotificationService {
    @Autowired
    private UserRepositry userRepositry;
    @Autowired MailProducerService mailProducerService;

    public void NotifyAll(String subject, String body){
        int page =0 ;
        int batchSize = 100; //can change acc. to server configuration and userList size
        Page<User> usersPage;

        do{
            Pageable pageable = PageRequest.of(page,batchSize);
             usersPage = userRepositry.findAll(pageable);
             List<User> users = usersPage.getContent();

            for(User user: users){
                mailProducerService.mailProducer(user.getEmail(),subject,body);
            }
            page++;
        }while (usersPage.hasNext());

    }
}
