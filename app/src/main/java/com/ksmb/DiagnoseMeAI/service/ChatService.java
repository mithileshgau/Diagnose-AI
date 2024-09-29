package com.ksmb.DiagnoseMeAI.service;


import com.ksmb.DiagnoseMeAI.model.LLMResponse;
import com.ksmb.DiagnoseMeAI.model.UserInputRequest;
import com.ksmb.DiagnoseMeAI.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ChatService {
    @Autowired
    GeminiService geminiService;

    // Process the user input, extract the data using LLM, and return the response
    public UserResponse processUserInput(UserInputRequest userInputRequest) throws IOException {
        LLMResponse llmResponse = extractInformationFromLLM(userInputRequest.getInputText());
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("Extracted data from user input");
        userResponse.setExtractedData(llmResponse.getExtractedInformation());
        return userResponse;
    }
    private LLMResponse extractInformationFromLLM(String inputText) throws IOException {
        String response = geminiService.prompt(inputText);
        Map<String, Object> extractedData = new HashMap<>();
        extractedData.put("response",response);
        // Assume LLM extracts a symptom and an age from the input text
        extractedData.put("symptoms", "cough, fever");
        extractedData.put("age", 30);
        return new LLMResponse(extractedData);
    }
}