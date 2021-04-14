package com.fis.upStream.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fis.upStream.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationController {

    @Autowired
    SimpMessagingTemplate messagingTemplate;
    @KafkaListener(
            topics = "${upstream.topic.inQueue}",
            groupId = "${upstream.consumer.group-id}"
    )
    public void listen(@Payload String str) throws JsonProcessingException {
        Notification notification = new ObjectMapper().readValue(str, Notification.class);
        messagingTemplate.convertAndSendToUser(notification.getReceiverId().toString(),"/queue/messages",notification);
    }
}
