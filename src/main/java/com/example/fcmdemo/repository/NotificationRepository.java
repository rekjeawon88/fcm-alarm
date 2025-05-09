package com.example.fcmdemo.repository;

import com.example.fcmdemo.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Notification 엔티티의 데이터 액세스 레포지토리 인터페이스
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // JpaRepository에서 제공하는 기본 CRUD 메서드 사용 가능
}
