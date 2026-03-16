package com.example.ComplainServiceApp.services;

import com.example.ComplainServiceApp.model.AiResponse;
import com.example.ComplainServiceApp.model.CompainCreatedEvent;
import com.example.ComplainServiceApp.entity.Complain;
import com.example.ComplainServiceApp.repositry.ComplainRepositry;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class AiProcessesService {
    private String apiKey = "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";

    @Autowired
    private ComplainService complainService;

    @Autowired
    private ComplainRepositry complainRepositry;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ComplianProducerService complianProducerService;

    @Autowired
    private MailProducerService mailProducerService;

   private final ExecutorService executor = Executors.newFixedThreadPool(10);


   public void processComplian(CompainCreatedEvent event){
       executor.submit(()->{
           try{
               String complainmsg = event.getComplain();
               AiResponse aiResponse = AiProcessing(complainmsg);
               if(aiResponse==null) {
                   log.error("rGroq is not working, all complign reviced in other type");
               }
               else{
                   log.info("Ai response is not null");
                   if (aiResponse.getSolution() != null) {
                       Optional<Complain> optionalComplain = complainRepositry.findById(event.getId());
                       if (optionalComplain.isPresent()) {
                           Complain complain = optionalComplain.get();
                           complain.setSolution(aiResponse.getSolution());
                           complain.setSolved(true);
                           complain.setLevel(aiResponse.getLevel());
                           complain.setType(aiResponse.getType());
                           log.info("Groq API Is working");
                           complainService.saveCompalin(complain);
                           //mailProducerService.mailProducer();
                       }
                   } else {
                       Optional<Complain> optionalComplain = complainRepositry.findById(event.getId());
                       if (optionalComplain.isPresent()) {
                           log.info("Ai service is working but Complign is of higher level");
                           Complain complain = optionalComplain.get();
                           complain.setSolved(true);
                           complain.setLevel(aiResponse.getLevel());
                           complain.setType(aiResponse.getType());
                       }
                   }
               }

           } catch (Exception e) {
               //complianProducerService.complainProduceEvent(event);
               log.error("AI Processing Failed for {}", event.getId(), e);
           }
       });
   }



   public AiResponse AiProcessing(String complian){

       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);
       headers.setBearerAuth(apiKey);

       String url =
               "https://api.groq.com/openai/v1/chat/completions";
       String prompt = """
       Analyze the complaint and return ONLY valid JSON.

       JSON format:
       {
        "sentiment": "POSITIVE | NEGATIVE | NEUTRAL",
        "type": "FINANCE | TECH | OTHER",
        "level": "HIGH | MEDIUM | LOW",
        "solution": "solution text if LOW else empty string"
        }

       Complaint:
       """ + complian;

       Map<String, Object> requestBody = Map.of(
               "model", "llama-3.1-8b-instant",
               "messages", List.of(
                       Map.of(
                               "role", "user",
                               "content", prompt
                       )
               ),
               "temperature", 0
       );

       HttpEntity<Map<String,Object>> entity = new HttpEntity<>(requestBody, headers);

       ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST,entity,Map.class);
       Map body = response.getBody();
       if(body==null){
           log.error("Groq response is null..");
           return null;
       }
       try {

           List<Map<String, Object>> choices =
                   (List<Map<String, Object>>) body.get("choices");

           Map<String, Object> message =
                   (Map<String, Object>) choices.get(0).get("message");

           String aiText = (String) message.get("content");

           aiText = aiText.replace("```json", "")
                   .replace("```", "")
                   .trim();

           log.info("AI RAW JSON -> {}", aiText);

           return objectMapper.readValue(aiText, AiResponse.class);

       } catch (Exception e) {
           log.error("Failed to parse Groq response", e);
           return null;
       }
   }
}

//curl "https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent" \
//        -H 'Content-Type: application/json' \
//        -H 'X-goog-api-key: AIzaSyAxlAvHFKNpBDDA-mf8vWPWjQJL-Yy_w64' \
//        -X POST \
//        -d '{
//        "contents": [
//        {
//        "parts": [
//        {
//        "text": "Explain how AI works in a few words"
//        }
//        ]
//        }
//        ]
//        }'
// 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  -