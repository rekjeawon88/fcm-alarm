package com.example.fcmdemo.service;

import com.example.fcmdemo.dto.NotificationRequest;
import com.example.fcmdemo.dto.NotificationResponse;
import com.example.fcmdemo.entity.Notification;
import com.example.fcmdemo.repository.NotificationRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public NotificationResponse sendNotification(NotificationRequest request) {
        try {
            // 알림 엔티티 생성
            Notification notification = new Notification(
                    request.getTitle(),
                    request.getBody(),
                    request.getToken()
            );

            // FCM 메시지 생성 - 수정된 부분
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

            // 데이터베이스에 저장
            notification = notificationRepository.save(notification);

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

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
}
