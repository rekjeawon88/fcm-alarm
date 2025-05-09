package com.example.fcmdemo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * FCM 알림 전송 결과를 나타내는 DTO 클래스
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {
    // 알림 ID
    private Long id;
    // 알림 제목
    private String title;
    // 알림 내용
    private String body;
    // 알림 수신자 토큰
    private String token;
    // 알림 전송 시간
    private LocalDateTime sentAt;
    // 알림 전송 성공 여부
    private boolean successful;
    // 알림 전송 결과 메시지
    private String message;

    /**
     * 알림 전송 결과를 나타내는 생성자
     *
     * @param id         알림 ID
     * @param title      알림 제목
     * @param body       알림 내용
     * @param token      알림 수신자 토큰
     * @param sentAt     알림 전송 시간
     * @param successful 알림 전송 성공 여부
     */
    public NotificationResponse(Long id, String title, String body, String token, LocalDateTime sentAt,
                                boolean successful) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.token = token;
        this.sentAt = sentAt;
        this.successful = successful;
        this.message = successful ? "알림이 성공적으로 전송되었습니다." : "알림 전송에 실패했습니다.";
    }

    /**
     * 알림 전송 실패 시 사용되는 생성자
     *
     * @param errorMessage 오류 메시지
     */
    public NotificationResponse(String errorMessage) {
        this.successful = false;
        this.message = errorMessage;
    }
}
