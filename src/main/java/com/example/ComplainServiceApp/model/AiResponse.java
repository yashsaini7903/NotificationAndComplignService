package com.example.ComplainServiceApp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AiResponse {
    private String sentiment;
    private String level;
    private String type;
    private String solution;
}
