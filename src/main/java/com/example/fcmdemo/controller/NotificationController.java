package com.example.fcmdemo.controller;

import com.example.fcmdemo.dto.NotificationRequest;
import com.example.fcmdemo.dto.NotificationResponse;
import com.example.fcmdemo.entity.Notification;
import com.example.fcmdemo.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FCM 알림 관련 API를 제공하는 컨트롤러 클래스
 */
@RestController
@RequestMapping("/api/notifications")
@Tag(name = "Notification API", description = "FCM 알림 관련 API")
public class NotificationController {

    // NotificationService 인터페이스를 주입받는 필드
    private final NotificationService notificationService;

    // NotificationService 인터페이스를 주입받는 생성자
    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * FCM을 통해 알림을 전송하는 API
     *
     * @param request 알림 전송 요청 정보
     * @return 알림 전송 결과
     */
    @PostMapping("/send")
    @Operation(summary = "알림 전송", description = "FCM을 통해 알림을 전송합니다.")
    public ResponseEntity<NotificationResponse> sendNotification(@RequestBody NotificationRequest request) {
        NotificationResponse response = notificationService.sendNotification(request);
        return ResponseEntity.ok(response);
    }

    /**
     * 전송된 모든 알림 목록을 조회하는 API
     *
     * @return 알림 목록
     */
    @GetMapping
    @Operation(summary = "알림 목록 조회", description = "전송된 모든 알림 목록을 조회합니다.")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }
}
