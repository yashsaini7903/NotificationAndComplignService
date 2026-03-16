package com.example.ComplainServiceApp.services;

import com.example.ComplainServiceApp.entity.Complain;
import com.example.ComplainServiceApp.repositry.ComplainRepositry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TeamService {

    @Autowired
    private ComplainRepositry complainRepositry;

    public List<Complain> FinanceHighService(){
        try{
            return complainRepositry.findByTypeAndSolvedAndLevel("FINANCE",false,"HIGH");
        } catch (Exception e) {
            log.error("error during fecthing finace compligns",e);
            throw new RuntimeException(e);
        }
    }
    public List<Complain> FinanceMidService(){
        try{
            return complainRepositry.findByTypeAndSolvedAndLevel("FINANCE",false,"MEDIUM");
        } catch (Exception e) {
            log.error("error during fecthing finace compligns",e);
            throw new RuntimeException(e);
        }
    }
    public List<Complain> FinanceLowService(){
        try{
            return complainRepositry.findByTypeAndSolvedAndLevel("FINANCE",false,"LOW");
        } catch (Exception e) {
            log.error("error during fecthing finace compligns",e);
            throw new RuntimeException(e);
        }
    }

    public List<Complain> TechHighService(){
        try{
            return complainRepositry.findByTypeAndSolvedAndLevel("TECH",false,"HIGH");
        } catch (Exception e) {
            log.error("error during fecthing TECH compligns",e);
            throw new RuntimeException(e);
        }
    }
    public List<Complain> TechMedService(){
        try{
            return complainRepositry.findByTypeAndSolvedAndLevel("TECH",false,"MEDIUM");
        } catch (Exception e) {
            log.error("error during fecthing TECH compligns",e);
            throw new RuntimeException(e);
        }
    }
    public List<Complain> TechLowService(){
        try{
            return complainRepositry.findByTypeAndSolvedAndLevel("TECH",false,"LOW");
        } catch (Exception e) {
            log.error("error during fecthing TECH compligns",e);
            throw new RuntimeException(e);
        }
    }

    public List<Complain> OtherHighService(){
        try{
            return complainRepositry.findByTypeAndSolvedAndLevel("OTHER",false,"HIGH");
        } catch (Exception e) {
            log.error("error during fecthing OTHER compligns",e);
            throw new RuntimeException(e);
        }
    }
    public List<Complain> OtherMidService(){
        try{
            return complainRepositry.findByTypeAndSolvedAndLevel("OTHER",false,"MEDIUM");
        } catch (Exception e) {
            log.error("error during fecthing OTHER compligns",e);
            throw new RuntimeException(e);
        }
    }
    public List<Complain> OtherLowService(){
        try{
            return complainRepositry.findByTypeAndSolvedAndLevel("OTHER",false,"LOW");
        } catch (Exception e) {
            log.error("error during fecthing OTHER compligns",e);
            throw new RuntimeException(e);
        }
    }
}
