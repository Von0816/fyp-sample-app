package com.example.messagingservice.service;

import com.example.messagingservice.dto.KeycloakUser;
import com.example.messagingservice.model.Notification;
import com.example.messagingservice.model.Status;
import com.example.messagingservice.proxy.AuthProxy;
import com.example.messagingservice.repository.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

import static java.util.Objects.isNull;


@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService{


    private  final EmailService emailService;
    private  final AuthProxy authProxy;
    private  final NotificationRepository notificationRepository;
    @Override
    public Notification send(Notification notification) {
        notification.setAttempt(1);
        if(isNull(notification.getData())){
            notification.setData(new HashMap<>());
        }
        processNotification(notification);
        return notificationRepository.save(notification);
    }

    private void processNotification(Notification notification){

        switch (notification.getNotificationType()){
            case EMAIL :

                processEmail(notification);
                break;
            default:
                break;
        }

    }

    private void processEmail(Notification notification) {


        switch (notification.getNotificationTemplate()){
            case NEW_SONG_COMMENT :

                sendNewSongComment(notification);
                break;
            default:
                break;
        }
    }

    private void sendNewSongComment(Notification notification) {
        try {

            if(Objects.equals(notification.getSenderUserId(),notification.getReceiverUserId())){
                notification.setStatus(Status.SENT);
                return;
            }
            String songTitle = (String)notification.getData().getOrDefault("SONG_TITLE","");

            KeycloakUser sendUser = authProxy.getUserById(notification.getSenderUserId()); // ordinary user or artist commenting
            KeycloakUser receiverUser = authProxy.getUserById(notification.getReceiverUserId()); // artist email
            emailService.sendSongCommentEmail(sendUser.getFirstName() +" "+sendUser.getLastName(),songTitle,notification.getMessage(),receiverUser.getEmail());
            notification.setStatus(Status.SENT);
        } catch (Exception e) {
           log.error("",e);
            notification.setStatus(Status.PENDING);
        }

    }


}
