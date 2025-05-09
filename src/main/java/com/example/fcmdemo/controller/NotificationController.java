package com.example.fcmdemo.controller;

import com.example.fcmdemo.dto.NotificationRequest;
import com.example.fcmdemo.dto.NotificationResponse;
import com.example.fcmdemo.entity.Notification;
import com.example.fcmdemo.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@Tag(name = "Notification API", description = "FCM 알림 관련 API")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    @Operation(summary = "알림 전송", description = "FCM을 통해 알림을 전송합니다.")
    public ResponseEntity<NotificationResponse> sendNotification(@RequestBody NotificationRequest request) {
        NotificationResponse response = notificationService.sendNotification(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "알림 목록 조회", description = "전송된 모든 알림 목록을 조회합니다.")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }
}
