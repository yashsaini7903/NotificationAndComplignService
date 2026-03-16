package com.example.ComplainServiceApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "Complians")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Complain {
    @Id
    private String Id;
    private String username;
//    @NonNull
    private String title;
//    @NonNull
    private String complain;
    private String type;
    private String solution;
    private Boolean solved;
    private String level;
    private LocalDateTime date;
}
