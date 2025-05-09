package com.example.fcmdemo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {
    private Long id;
    private String title;
    private String body;
    private String token;
    private LocalDateTime sentAt;
    private boolean successful;
    private String message;

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

    public NotificationResponse(String errorMessage) {
        this.successful = false;
        this.message = errorMessage;
    }
}
