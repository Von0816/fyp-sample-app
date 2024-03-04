package com.example.messagingservice.api;


import com.example.messagingservice.model.Notification;
import com.example.messagingservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationApi {

    private final NotificationService notificationService;



    @PostMapping("/send")
    public Notification send(@RequestBody Notification notification){

        return notificationService.send(notification);
    }
}
