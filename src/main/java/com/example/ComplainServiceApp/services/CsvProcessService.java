package com.example.ComplainServiceApp.services;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

@Service
@Slf4j
public class CsvProcessService{

    @Autowired
    private MailProducerService mailProducerService;


    public void ProcessCsv(MultipartFile file , String subject, String body){
        try(Reader reader = new InputStreamReader(file.getInputStream());
            CSVReader csvReader = new CSVReader(reader);)
        {
            String[] row;
            while( (row = csvReader.readNext()) !=null){
                String email = row[1];
                mailProducerService.mailProducer(email, subject ,body);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
