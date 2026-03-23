package com.cauanlagrotta.magalums.controller;

import com.cauanlagrotta.magalums.controller.dto.ScheduleNotificationDTO;
import com.cauanlagrotta.magalums.entity.Notification;
import com.cauanlagrotta.magalums.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
  private final NotificationService notificationService;

  public NotificationController(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @PostMapping
  public ResponseEntity<Void> scheduleNotification(@RequestBody ScheduleNotificationDTO dto){
    notificationService.scheduleNotification(dto);
    return ResponseEntity.accepted().build();
  }

  @GetMapping("/{notificationId}")
  public ResponseEntity<Notification> getNotification(@PathVariable("notificationId") Long notificationId){
    var notification = notificationService.findById(notificationId);

    return notification.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }
}
