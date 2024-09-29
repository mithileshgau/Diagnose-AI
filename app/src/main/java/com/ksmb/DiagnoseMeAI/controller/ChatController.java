package com.ksmb.DiagnoseMeAI.controller;

import com.ksmb.DiagnoseMeAI.model.UserInputRequest;
import com.ksmb.DiagnoseMeAI.model.UserResponse;
import com.ksmb.DiagnoseMeAI.service.ChatService;
import com.ksmb.DiagnoseMeAI.service.DiseasePredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/chatbot")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private DiseasePredictionService diseasePredictionService;


    @PostMapping("/input")
    public ResponseEntity<UserResponse> handleUserInput(@RequestBody UserInputRequest userInputRequest) throws IOException {
        return ResponseEntity.ok(chatService.processUserInput(userInputRequest));
    }
    @PostMapping("/helloWorld")
    public ResponseEntity<String> helloWorld(@RequestBody UserInputRequest userInputRequest) {
        return ResponseEntity.ok("HelloWorld");
    }

    @PostMapping("/predict")
    public ResponseEntity<String> predictDisease(@RequestBody UserInputRequest userInputRequest){
        try {
            String result = diseasePredictionService.runPythonScript(userInputRequest.getInputText());
            System.out.println(result);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }

}
