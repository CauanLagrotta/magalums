package com.cauanlagrotta.magalums.repository;

import com.cauanlagrotta.magalums.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
