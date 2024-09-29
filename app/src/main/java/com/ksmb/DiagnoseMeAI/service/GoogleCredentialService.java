package com.ksmb.DiagnoseMeAI.service;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
@Service
public class GoogleCredentialService {
    AccessToken token;
    GoogleCredentials credentials;

    GoogleCredentialService() throws IOException {
        this.credentials = GoogleCredentials.fromStream(new FileInputStream("C:\\Personal\\Diagnose-AI\\app\\src\\main\\resources\\diagnosemeai-aee1af7331b2.json"))
                .createScoped(Arrays.asList(
                        "https://www.googleapis.com/auth/cloud-platform",
                        "https://apiplatform.googleapis.com"
                ));
        this.credentials.refreshIfExpired();
        this.token = credentials.getAccessToken();
    }

}

