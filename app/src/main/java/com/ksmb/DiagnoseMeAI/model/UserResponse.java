package com.ksmb.DiagnoseMeAI.model;

import java.util.Map;

public class UserResponse {
    private String message;
    private Map<String, Object> extractedData;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getExtractedData() {
        return extractedData;
    }

    public void setExtractedData(Map<String, Object> extractedData) {
        this.extractedData = extractedData;
    }
// Getters and setters
}
