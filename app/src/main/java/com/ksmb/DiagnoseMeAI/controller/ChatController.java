package com.ksmb.DiagnoseMeAI.controller;

import com.ksmb.DiagnoseMeAI.model.UserInputRequest;
import com.ksmb.DiagnoseMeAI.model.UserResponse;
import com.ksmb.DiagnoseMeAI.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @PostMapping("/input")
    public ResponseEntity<UserResponse> handleUserInput(@RequestBody UserInputRequest userInputRequest) throws IOException {
        return ResponseEntity.ok(chatService.processUserInput(userInputRequest));
    }
    @GetMapping("/helloWorld")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("HelloWorld");
    }
}
