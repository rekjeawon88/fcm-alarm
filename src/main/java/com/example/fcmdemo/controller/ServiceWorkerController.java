package com.example.fcmdemo.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Controller
public class ServiceWorkerController {

    @GetMapping(value = "/firebase-messaging-sw.js", produces = "application/javascript")
    @ResponseBody
    public ResponseEntity<String> getServiceWorkerJs() throws IOException {
        Resource resource = new ClassPathResource("static/firebase-messaging-sw.js");
        String content = new String(Files.readAllBytes(resource.getFile().toPath()), StandardCharsets.UTF_8);

        // Service-Worker-Allowed 헤더 추가
        HttpHeaders headers = new HttpHeaders();
        headers.add("Service-Worker-Allowed", "/");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/javascript"))
                .body(content);
    }
}
