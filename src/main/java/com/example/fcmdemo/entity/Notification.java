package com.example.fcmdemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * FCM 알림 정보를 나타내는 엔티티 클래스
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    // 알림 ID (기본 키)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 알림 제목 (필수)
    @Column(nullable = false)
    private String title;

    // 알림 내용 (필수)
    @Column(nullable = false)
    private String body;

    // 알림 수신자 토큰 (필수)
    @Column(nullable = false)
    private String token;

    // 알림 전송 시간 (필수)
    @Column(nullable = false)
    private LocalDateTime sentAt;

    // 알림 전송 성공 여부
    @Column
    private boolean successful;

    /**
     * 알림 정보를 생성하는 커스텀 생성자
     *
     * @param title 알림 제목
     * @param body  알림 내용
     * @param token 알림 수신자 토큰
     */
    public Notification(String title, String body, String token) {
        this.title = title;
        this.body = body;
        this.token = token;
        this.sentAt = LocalDateTime.now();
    }
}
