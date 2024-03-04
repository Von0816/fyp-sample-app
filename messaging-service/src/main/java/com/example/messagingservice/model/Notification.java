package com.example.messagingservice.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String message;
    private String senderUserId;
    private String receiverUserId;
    private Integer attempt;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private NotificationTemplate notificationTemplate;
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
    @Convert(converter = MapConverter.class)
    private Map<String,Object> data;

}
