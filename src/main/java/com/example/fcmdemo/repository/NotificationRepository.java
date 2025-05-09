package com.example.fcmdemo.repository;

import com.example.fcmdemo.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // 기본 CRUD 메서드는 JpaRepository에서 제공됨
}
