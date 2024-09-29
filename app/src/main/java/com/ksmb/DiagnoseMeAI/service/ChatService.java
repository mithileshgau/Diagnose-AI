package com.ksmb.DiagnoseMeAI.service;


import com.ksmb.DiagnoseMeAI.model.LLMResponse;
import com.ksmb.DiagnoseMeAI.model.UserInputRequest;
import com.ksmb.DiagnoseMeAI.model.UserResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatService {

    // Simulate the LLM interaction with this method
    private LLMResponse extractInformationFromLLM(String inputText) {
        // Mocking the LLM's extraction process
        Map<String, Object> extractedData = new HashMap<>();
        // Assume LLM extracts a symptom and an age from the input text
        extractedData.put("symptoms", "cough, fever");
        extractedData.put("age", 30);
        return new LLMResponse(extractedData);
    }

    // Process the user input, extract the data using LLM, and return the response
    public UserResponse processUserInput(UserInputRequest userInputRequest) {
        // Extract information from LLM (mocked here)
        LLMResponse llmResponse = extractInformationFromLLM(userInputRequest.getInputText());

        // Create a user response
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("Extracted data from user input");
        userResponse.setExtractedData(llmResponse.getExtractedInformation());

        return userResponse;
    }
}