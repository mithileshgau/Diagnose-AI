package com.ksmb.DiagnoseMeAI.service;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

@Configuration
public class GoogleCredentialService {


    @Bean
    public GoogleCredentials googleCredentials() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new FileInputStream("C:\\Personal\\Diagnose-AI\\app\\src\\main\\resources\\diagnosemeai-aee1af7331b2.json"))
                .createScoped(Arrays.asList(
                        "https://www.googleapis.com/auth/cloud-platform"
                ));
        googleCredentials.refreshIfExpired();
        return googleCredentials;
    }


    @Bean
    public AccessToken googleAccessToken(GoogleCredentials googleCredentials) throws IOException {
        return googleCredentials.getAccessToken();
    }
}
