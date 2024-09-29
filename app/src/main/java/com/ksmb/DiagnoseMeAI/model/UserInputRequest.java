package com.ksmb.DiagnoseMeAI.model;


public class UserInputRequest {
    private String userId;
    private String inputText;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }
}
