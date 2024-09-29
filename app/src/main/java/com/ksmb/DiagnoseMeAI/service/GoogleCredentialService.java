package com.ksmb.DiagnoseMeAI.service;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@Configuration
public class GoogleCredentialService {


    @Bean
    public GoogleCredentials googleCredentials() throws IOException {

        InputStream in = this.getClass().getResourceAsStream("/diagnosemeai-service-key.json");
//        InputStream in = this.getClass().getResourceAsStream("/diagnosemeai-aee1af7331b2.json");

        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(in)
                .createScoped(Arrays.asList(
                        "https://www.googleapis.com/auth/cloud-platform"
                ));
        return googleCredentials;
    }


    @Bean
    public AccessToken googleAccessToken(GoogleCredentials googleCredentials) throws IOException {
        return googleCredentials.getAccessToken();
    }
}
