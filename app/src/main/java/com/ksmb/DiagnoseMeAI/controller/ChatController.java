package com.ksmb.DiagnoseMeAI.controller;

import com.ksmb.DiagnoseMeAI.model.UserInputRequest;
import com.ksmb.DiagnoseMeAI.model.UserResponse;
import com.ksmb.DiagnoseMeAI.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/input")
    public ResponseEntity<UserResponse> handleUserInput(@RequestBody UserInputRequest userInputRequest) throws IOException {
        return ResponseEntity.ok(chatService.processUserInput(userInputRequest));
    }
    @PostMapping("/helloWorld")
    public ResponseEntity<String> helloWorld(@RequestBody UserInputRequest userInputRequest) {
        return ResponseEntity.ok("HelloWorld");
    }
}
