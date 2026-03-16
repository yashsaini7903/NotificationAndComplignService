package com.example.ComplainServiceApp.controllers;

import com.example.ComplainServiceApp.model.NotificationMail;
import com.example.ComplainServiceApp.services.NotificationService;
import com.example.ComplainServiceApp.services.UserSevice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@Slf4j
@RestController
@RequestMapping("/notify")
public class NotificationController{
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/all")
    public ResponseEntity<?> notifyAll(@RequestBody NotificationMail notificationMail){
      try {
          notificationService.NotifyAll(notificationMail.getSubject(), notificationMail.getBody());
          return new ResponseEntity<>(HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
      }
    }

}
