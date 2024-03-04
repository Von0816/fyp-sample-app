package com.example.messagingservice.service;

import com.example.messagingservice.model.Notification;

public interface NotificationService {

    Notification send(Notification notification);
}
