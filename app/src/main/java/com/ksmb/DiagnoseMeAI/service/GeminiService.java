package com.ksmb.DiagnoseMeAI.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vertexai.Transport;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.preview.ChatSession;
import com.google.cloud.vertexai.generativeai.preview.GenerativeModel;
import com.google.cloud.vertexai.generativeai.preview.ResponseHandler;
import com.ksmb.DiagnoseMeAI.configuration.GoogleCredentialsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GeminiService {

    private GoogleCredentials googleCredentials;

    private VertexAI vertexAI;

    private GenerativeModel model;

    private ChatSession chat;

    @Autowired
    GeminiService(GoogleCredentials googleCredentials) throws IOException {
        this.googleCredentials = googleCredentials;
        this.googleCredentials.refresh();
        if(this.googleCredentials.getAccessToken() == null){
            throw  new IOException("Error loading credentials");
        }
        this.vertexAI = new VertexAI("diagnosemeai", "us-central1",Transport.REST,googleCredentials);
        this.model = new GenerativeModel("gemini-pro", vertexAI);
        this.chat = model.startChat();
    }

    public String prompt(String inputText) throws IOException {
        GenerateContentResponse response = this.chat.sendMessage(inputText);
        return ResponseHandler.getText(response);

    }
}