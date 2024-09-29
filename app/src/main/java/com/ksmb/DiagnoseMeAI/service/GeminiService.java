package com.ksmb.DiagnoseMeAI.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vertexai.Transport;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.preview.GenerativeModel;
import com.google.cloud.vertexai.generativeai.preview.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GeminiService {
    @Autowired
    static GoogleCredentials googleCredential;

    public static void main(String[] args) {
        try{
            VertexAI vertexAI = new VertexAI("diagnosemeai","us-central1",googleCredential);
            GenerativeModel model = new GenerativeModel("gemini-ultra", vertexAI);
            GenerateContentResponse response = model.generateContent("How are you?");
            System.out.println(ResponseHandler.getText(response));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}