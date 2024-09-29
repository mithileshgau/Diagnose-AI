package com.ksmb.DiagnoseMeAI.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class LLMResponse {
    private Map<String, Object> extractedInformation;  // Key-value pairs for extracted information (e.g., symptoms, age, etc.)

    public LLMResponse(Map<String, Object> extractedData) {
    }
}
