package com.example.ComplainServiceApp.controllers;

import com.example.ComplainServiceApp.entity.Complain;
import com.example.ComplainServiceApp.model.NotificationMail;
import com.example.ComplainServiceApp.services.AddSolutionService;
import com.example.ComplainServiceApp.services.CsvProcessService;
import com.example.ComplainServiceApp.services.TeamService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@RequestMapping("/team")
public class TeamsController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private AddSolutionService addSolutionService;

    @Autowired
    private CsvProcessService csvProcessService;

    @GetMapping("/finance/high")
    public ResponseEntity<List<Complain>> FinaceHigh(){
       try{
          List<Complain> complains = teamService.FinanceHighService();
          return new ResponseEntity<>(complains, HttpStatus.OK);
       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
    }

    @GetMapping("/finance/med")
    public ResponseEntity<List<Complain>> FinaceMed(){
        try{
            List<Complain> complains = teamService.FinanceMidService();
            return new ResponseEntity<>(complains, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("/finance/low")
    public ResponseEntity<List<Complain>> FinaceLow(){
        try{
            List<Complain> complains = teamService.FinanceLowService();
            return new ResponseEntity<>(complains, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/tech/High")
    public ResponseEntity<List<Complain>> TechHigh(){
        try{
            List<Complain> complains = teamService.TechHighService();
            return new ResponseEntity<>(complains, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("/tech/med")
    public ResponseEntity<List<Complain>> TechMed(){
        try{
            List<Complain> complains = teamService.TechMedService();
            return new ResponseEntity<>(complains, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("/tech/low")
    public ResponseEntity<List<Complain>> TechLow(){
        try{
            List<Complain> complains = teamService.TechLowService();
            return new ResponseEntity<>(complains, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/other/High")
    public ResponseEntity<List<Complain>> OtherHigh(){
        try{
            List<Complain> complains = teamService.OtherHighService();
            return new ResponseEntity<>(complains, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("/other/med")
    public ResponseEntity<List<Complain>> OtherMed(){
        try{
            List<Complain> complains = teamService.OtherMidService();
            return new ResponseEntity<>(complains, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("/other/low")
    public ResponseEntity<List<Complain>> OtherLow(){
        try{
            List<Complain> complains = teamService.OtherLowService();
            return new ResponseEntity<>(complains, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @PostMapping("/addSolution/{Complign_Id}")
    public ResponseEntity<?> AddSolution(@RequestBody Map<String,String> body, @PathVariable String Complign_Id){
       try{
           String Solution = body.get("solution");
          Complain complain = addSolutionService.Solution(Solution, Complign_Id);
          return new ResponseEntity<>(complain, HttpStatus.OK);
       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }
    @PostMapping("/CsvMail")
    public ResponseEntity<?> ByCsv(@RequestBody NotificationMail mail, @RequestParam MultipartFile file){
     csvProcessService.ProcessCsv(file, mail.getSubject(), mail.getBody());
     return new ResponseEntity<>(HttpStatus.OK);
    }
}
