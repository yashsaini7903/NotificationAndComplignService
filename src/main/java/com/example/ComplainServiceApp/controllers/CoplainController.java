package com.example.ComplainServiceApp.controllers;

import com.example.ComplainServiceApp.entity.Complain;
import com.example.ComplainServiceApp.services.ComplainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Component
@Slf4j
@RestController
@RequestMapping("/complian")
public class CoplainController {


    @Autowired
    private ComplainService complainService;

    @PostMapping
    public ResponseEntity<?> complain(@RequestBody Complain complain){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            complain.setUsername(authentication.getName());
            complain.setDate(LocalDateTime.now());
            complain.setSolved(false);
            Complain savedComplian = complainService.saveComplainToprocess(complain);
            return new ResponseEntity<>(savedComplian, HttpStatus.OK);
        } catch (Exception e) {
            log.error("complian not saved",e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
