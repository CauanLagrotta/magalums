package com.cauanlagrotta.magalums.service;

import com.cauanlagrotta.magalums.controller.dto.ScheduleNotificationDTO;
import com.cauanlagrotta.magalums.entity.Notification;
import com.cauanlagrotta.magalums.entity.Status;
import com.cauanlagrotta.magalums.repository.NotificationRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class NotificationService {
  private final NotificationRepository notificationRepository;

  public NotificationService(NotificationRepository notificationRepository) {
    this.notificationRepository = notificationRepository;
  }

  public void scheduleNotification(ScheduleNotificationDTO dto){
    notificationRepository.save(dto.toNotification());
  }

  public Optional<Notification> findById(Long notificationId){
    return notificationRepository.findById(notificationId);
  }

  public void cancelNotification(Long notificationId){
    var notification = findById(notificationId);
    if(notification.isPresent()){
      notification.get().setStatus(Status.Values.CANCELLED.toStatus());
      notificationRepository.save(notification.get());
    }
  }

  public void checkAndSend(LocalDateTime dateTime){
    var notifications = notificationRepository.findByStatusInAndDateTimeBefore(List.of(
          Status.Values.PENDING.toStatus(),
          Status.Values.ERROR.toStatus()
        ), dateTime);

    notifications.forEach(sendNotification());
  }

  private Consumer<Notification> sendNotification() {
    return n -> {
      n.setStatus(Status.Values.SUCCESS.toStatus());
      notificationRepository.save(n);
    };
  }
}
