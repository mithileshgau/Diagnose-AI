package com.ksmb.DiagnoseMeAI.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vertexai.Transport;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.preview.ChatSession;
import com.google.cloud.vertexai.generativeai.preview.GenerativeModel;
import com.google.cloud.vertexai.generativeai.preview.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.io.IOException;
@DependsOn("googleCredentials")
@Service
public class GeminiService {
    @Autowired
    private GoogleCredentials googleCredentials;

    private VertexAI vertexAI;

    private GenerativeModel model;

    private ChatSession chat;


    GeminiService() throws IOException {
        vertexAI = new VertexAI("diagnosemeai", "us-east1",Transport.REST,googleCredentials);
        model = new GenerativeModel("gemini-pro", vertexAI);
        chat = model.startChat();
    }

    public String prompt(String inputText) throws IOException {
        GenerateContentResponse response = chat.sendMessage(inputText);
        return ResponseHandler.getText(response);
    }
}