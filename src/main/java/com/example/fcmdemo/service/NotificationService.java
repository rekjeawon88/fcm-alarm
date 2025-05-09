package com.example.fcmdemo.service;

import com.example.fcmdemo.dto.NotificationRequest;
import com.example.fcmdemo.dto.NotificationResponse;
import com.example.fcmdemo.entity.Notification;
import com.example.fcmdemo.repository.NotificationRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FCM 알림 관련 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service
public class NotificationService {

    // NotificationRepository 인터페이스를 주입받는 필드
    private final NotificationRepository notificationRepository;

    // NotificationRepository 인터페이스를 주입받는 생성자
    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    /**
     * FCM을 통해 알림을 전송하는 메서드
     *
     * @param request 알림 전송 요청 정보
     * @return 알림 전송 결과
     */
    public NotificationResponse sendNotification(NotificationRequest request) {
        try {
            // 알림 엔티티 생성
            Notification notification = new Notification(
                    request.getTitle(),
                    request.getBody(),
                    request.getToken()
            );

            // FCM 메시지 생성
            Message message = Message.builder()
                    .setToken(request.getToken())
                    .setNotification(com.google.firebase.messaging.Notification.builder()
                            .setTitle(request.getTitle())
                            .setBody(request.getBody())
                            .build())
                    .build();

            // FCM으로 메시지 전송
            String messageId = FirebaseMessaging.getInstance().send(message);

            // 전송 성공 설정
            notification.setSuccessful(true);

            // 데이터베이스에 알림 정보 저장
            notification = notificationRepository.save(notification);

            // 알림 전송 결과 반환
            return new NotificationResponse(
                    notification.getId(),
                    notification.getTitle(),
                    notification.getBody(),
                    notification.getToken(),
                    notification.getSentAt(),
                    notification.isSuccessful()
            );
        } catch (Exception e) {
            // 오류 발생 시 실패 응답 반환
            return new NotificationResponse("알림 전송 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * 모든 알림 정보를 조회하는 메서드
     *
     * @return 알림 목록
     */
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
}
