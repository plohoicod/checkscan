package org.example.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FirebaseInit {

    @PostConstruct
    public void init() throws IOException {

        FileInputStream serviceAccount =
                new FileInputStream("./key.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://checkscan-8a1d9.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);

    }
}
