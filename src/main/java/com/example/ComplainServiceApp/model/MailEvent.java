package com.example.ComplainServiceApp.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailEvent{
    @NonNull
    private String mail;
    private String subject;
    @NonNull
    private String body;
}
