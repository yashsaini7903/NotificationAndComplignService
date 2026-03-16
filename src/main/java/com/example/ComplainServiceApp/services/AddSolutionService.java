package com.example.ComplainServiceApp.services;

import com.example.ComplainServiceApp.entity.Complain;
import com.example.ComplainServiceApp.repositry.ComplainRepositry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AddSolutionService {
    @Autowired
    private ComplainRepositry complainRepositry;

    @Autowired
    private ComplainService complainService;

    public Complain Solution(String solution, String id){
        try {
            Optional<Complain> complain = complainRepositry.findById(id);
            if(complain.isPresent()){
                Complain complainFound = complain.get();
                complainFound.setSolution(solution);
                complainService.saveCompalin(complainFound);
                log.info("solution added of complign :"+ id);
                return complainFound;
            }
        } catch (Exception e) {
            log.error("during adding solution of complign:" +id ,e);
            throw new RuntimeException(e);
        }
        return null;
    }
}
