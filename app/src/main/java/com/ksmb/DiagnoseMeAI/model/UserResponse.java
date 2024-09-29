package com.ksmb.DiagnoseMeAI.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class UserResponse {
    private String message;
    private Map<String, Object> extractedData;

    // Getters and setters
}
