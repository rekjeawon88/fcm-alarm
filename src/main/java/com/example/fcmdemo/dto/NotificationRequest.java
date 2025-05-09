package com.example.fcmdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * FCM 알림 전송 요청 정보를 담는 DTO 클래스
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {
    // 알림 제목
    private String title;

    // 알림 내용
    private String body;

    // 알림을 받을 기기의 토큰
    private String token;
}
