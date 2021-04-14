package com.fis.streamService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fis.streamService.model.NotificationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificationEventController {

    @Autowired
    @Qualifier("stream-service")
    private KafkaTemplate<String, NotificationEvent> messageKafkaTemplate;
    @Value("${upstream.connection.in.queue}")
    public String auctionSystemInQueue;

    @KafkaListener(
            topics = "${downstream.connection.in.queue}",
            groupId = "${downstream.consumer.group-id}",
            containerFactory = "notificationKafkaListenerContainerFactory"
    )
    public void listen(@Payload String str) throws JsonProcessingException {

        NotificationEvent notificationEvent = new ObjectMapper().readValue(str, NotificationEvent.class);
        messageKafkaTemplate.send(auctionSystemInQueue,"key-"+notificationEvent.getReceiverId().toString(), notificationEvent);
    }
}

