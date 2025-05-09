
package com.example.fcmdemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime sentAt;

    @Column
    private boolean successful;

    // 커스텀 생성자
    public Notification(String title, String body, String token) {
        this.title = title;
        this.body = body;
        this.token = token;
        this.sentAt = LocalDateTime.now();
    }
}
