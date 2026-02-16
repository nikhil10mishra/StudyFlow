package com.studyplanner.studyplanner.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirestoreConfig {

    @Bean
    public Firestore firestore() throws Exception {

        String firebaseConfig = System.getenv("FIREBASE_CONFIG");

        if (firebaseConfig == null || firebaseConfig.isEmpty()) {
            throw new IllegalStateException("FIREBASE_CONFIG environment variable not set");
        }

        GoogleCredentials credentials =
                GoogleCredentials.fromStream(
                        new ByteArrayInputStream(firebaseConfig.getBytes(StandardCharsets.UTF_8))
                );

        FirestoreOptions options = FirestoreOptions.newBuilder()
                .setCredentials(credentials)
                .build();

        return options.getService();
    }
}
