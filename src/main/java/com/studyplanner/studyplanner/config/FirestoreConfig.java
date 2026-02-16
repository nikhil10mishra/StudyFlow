package com.studyplanner.studyplanner.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirestoreConfig {

    @Bean
    public Firestore firestore() throws Exception {

        String firebaseConfig = System.getenv("FIREBASE_CONFIG");

        GoogleCredentials credentials;

        if (firebaseConfig != null && !firebaseConfig.isBlank()) {
            // Production (Render)
            credentials = GoogleCredentials.fromStream(
                    new ByteArrayInputStream(firebaseConfig.getBytes(StandardCharsets.UTF_8))
            );
        } else {
            // Local development
            credentials = GoogleCredentials.fromStream(
                    new FileInputStream("firebase-service-account.json")
            );
        }

        FirestoreOptions options = FirestoreOptions.newBuilder()
                .setCredentials(credentials)
                .build();

        return options.getService();
    }
}
