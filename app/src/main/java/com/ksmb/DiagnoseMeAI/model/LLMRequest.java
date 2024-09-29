package com.ksmb.DiagnoseMeAI.model;


public class LLMRequest {
    private String inputText;
    private String requestText;
    private final String PROMPT = "";

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    // Getters and setters
    LLMRequest(String inputText) {
        this.inputText = inputText;
        this.requestText = PROMPT + "---" + this.inputText;
    }
}
