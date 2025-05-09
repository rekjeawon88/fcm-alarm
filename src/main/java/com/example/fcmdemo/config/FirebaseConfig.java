package com.example.fcmdemo.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * Firebase 서비스 연동을 위한 구성 클래스
 */
@Configuration
public class FirebaseConfig {

    // Firebase 서비스 계정 파일 경로를 주입받는 필드
    @Value("${firebase.service-account-file}")
    private Resource serviceAccountResource;

    /**
     * Firebase 앱 초기화 및 반환 메서드
     *
     * @return 초기화된 FirebaseApp 객체
     * @throws IOException 서비스 계정 파일 읽기 중 발생할 수 있는 예외
     */
    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        // 이미 초기화된 Firebase 앱이 있는지 확인
        for (FirebaseApp app : FirebaseApp.getApps()) {
            if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
                return app;
            }
        }

        // 새로운 Firebase 앱 초기화
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccountResource.getInputStream()))
                .build();

        return FirebaseApp.initializeApp(options);
    }
}
