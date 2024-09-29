package com.ksmb.DiagnoseMeAI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class DiseasePredictionService {

    @Autowired
    GeminiService geminiService;

    public String runPythonScript(String input) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("python", "../model/predict_disease.py");
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder result = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            result.append(line);
        }

        process.waitFor();
        String prompt = "I have symptoms for: " + result.toString() + "Give me precautions to take for this disease";

        return geminiService.prompt(prompt);
    }
}
