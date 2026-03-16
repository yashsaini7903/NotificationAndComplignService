package com.example.ComplainServiceApp.model;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationMail {
    @NonNull
    private String subject;
    private String body;
}
