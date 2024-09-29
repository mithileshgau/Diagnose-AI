package com.ksmb.DiagnoseMeAI.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DiseasePredictionService {
    public String runPythonScript(String input) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("python", "C:/Users/SANKET/Documents/Projects/Diagnose-AI/model/predict_disease.py");
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder result = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            result.append(line);
        }

        process.waitFor();
        return result.toString();
    }
}
