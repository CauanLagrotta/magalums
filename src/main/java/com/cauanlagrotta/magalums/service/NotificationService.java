package com.cauanlagrotta.magalums.service;

import com.cauanlagrotta.magalums.controller.dto.ScheduleNotificationDTO;
import com.cauanlagrotta.magalums.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
  private final NotificationRepository notificationRepository;

  public NotificationService(NotificationRepository notificationRepository) {
    this.notificationRepository = notificationRepository;
  }

  public void scheduleNotification(ScheduleNotificationDTO dto){
    notificationRepository.save(dto.toNotification());
  }
}
