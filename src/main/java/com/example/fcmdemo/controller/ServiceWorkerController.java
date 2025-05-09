package com.example.fcmdemo.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Service Worker 관련 요청을 처리하는 컨트롤러 클래스
 */
@Controller
public class ServiceWorkerController {

    /**
     * Firebase Messaging Service Worker 스크립트를 반환하는 API
     *
     * @return Service Worker 스크립트 내용
     * @throws IOException 리소스 읽기 중 발생할 수 있는 예외
     */
    @GetMapping(value = "/firebase-messaging-sw.js", produces = "application/javascript")
    @ResponseBody
    public ResponseEntity<String> getServiceWorkerJs() throws IOException {
        // 클래스 패스의 static/firebase-messaging-sw.js 리소스 읽기
        Resource resource = new ClassPathResource("static/firebase-messaging-sw.js");
        String content = new String(Files.readAllBytes(resource.getFile().toPath()), StandardCharsets.UTF_8);

        // Service Worker 관련 HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("Service-Worker-Allowed", "/");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        // 리소스 내용과 헤더를 포함한 ResponseEntity 반환
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/javascript"))
                .body(content);
    }
}
