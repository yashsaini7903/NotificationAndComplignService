package com.example.ComplainServiceApp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompainCreatedEvent {
    private String Id;
    private String username;
    private String title;
    private String complain;

}
