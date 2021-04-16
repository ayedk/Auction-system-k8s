package com.fis.streamService.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fis.streamService.model.BidEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
@Component
public class BidEventController {

    @Autowired
    @Qualifier("auction-system")
    private KafkaTemplate<String, BidEvent> bidkafkaTemplate;
    @Value("${downstream.connection.in.queue}")
    String downStreamOutQueue;
    @KafkaListener(
            topics = "${upstream.connection.out.queue}",
            groupId = "${upstream.consumer.group-id}",
            containerFactory = "bidKafkaListenerContainerFactory"
    )
    public void listen(@Payload String str) throws JsonProcessingException {
        BidEvent bidEvent = new ObjectMapper().readValue(str, BidEvent.class);
        bidkafkaTemplate.send(downStreamOutQueue,bidEvent.getAuctionId().toString(), bidEvent);
    }
}