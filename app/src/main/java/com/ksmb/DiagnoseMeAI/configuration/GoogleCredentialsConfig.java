package com.ksmb.DiagnoseMeAI.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@Configuration
public class GoogleCredentialsConfig {
    @Value("${safe.resource.path}")
    String safeResourcePath;

    @Bean
    public GoogleCredentials googleCredentials() throws IOException {
        Resource resource = new ClassPathResource(safeResourcePath);
        InputStream in = resource.getInputStream();

        if (in == null) {
            throw new IOException("Credentials file not found");
        }

        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(in)
                .createScoped(Arrays.asList(
                        "https://www.googleapis.com/auth/cloud-platform"
                ));
        googleCredentials.refreshIfExpired();
        googleCredentials.refresh();
        if (googleCredentials.getAccessToken() == null) {
            throw new IOException("Credentials are null");
        }

        return googleCredentials;
    }
}
