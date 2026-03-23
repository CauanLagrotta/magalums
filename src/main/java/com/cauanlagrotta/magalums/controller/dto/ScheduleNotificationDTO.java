package com.cauanlagrotta.magalums.controller.dto;

import com.cauanlagrotta.magalums.entity.Channel;
import com.cauanlagrotta.magalums.entity.Notification;
import com.cauanlagrotta.magalums.entity.Status;

import java.time.LocalDateTime;

public record ScheduleNotificationDTO(LocalDateTime dateTime,
                                      String destination,
                                      String message,
                                      Channel.Values channel) {

  public Notification toNotification() {
    return new Notification(
        dateTime,
        destination,
        message,
        channel.toChannel(),
        Status.Values.PENDING.toStatus()

    );
  }
}
