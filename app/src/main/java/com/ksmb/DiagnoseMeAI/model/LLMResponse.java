package com.ksmb.DiagnoseMeAI.model;

import java.util.Map;

public class LLMResponse {
    private Map<String, Object> extractedInformation;  // Key-value pairs for extracted information (e.g., symptoms, age, etc.)

    public Map<String, Object> getExtractedInformation() {
        return extractedInformation;
    }

    public void setExtractedInformation(Map<String, Object> extractedInformation) {
        this.extractedInformation = extractedInformation;
    }

    public LLMResponse(Map<String, Object> extractedData) {
    }
}
