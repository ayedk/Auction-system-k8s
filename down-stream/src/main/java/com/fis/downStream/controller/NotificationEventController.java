package com.fis.downStream.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fis.downStream.model.Bid;
import com.fis.downStream.model.NotificationEvent;
import com.fis.downStream.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Component
public class NotificationEventController {

    @Autowired
    private KafkaTemplate<String, NotificationEvent> messageKafkaTemplate;
    @Autowired
    private BidService bidService;
    @Value("${downstream.connection.out.queue}")
    public String downStreamInQueue;
    String downStreamOutQueue;
    @KafkaListener(
            topics = "${downstream.connection.in.queue}",
            groupId = "${stream-api.consumer.group-id}"
    )
    public void listen(@Payload String str) throws JsonProcessingException {

        Bid bid = new ObjectMapper().readValue(str, Bid.class);
        List<Integer> buyerIds = bidService.getBuyerIds(bid.getAuctionId());
        for(Integer id : buyerIds){
            if(id!=bid.getBuyerId()){
                StringBuilder content = new StringBuilder();
                content.append("New Bid placed with a price: ");
                content.append(bid.getBidPrice().toString());
                content.append(" at the Time: ");
                content.append(bid.getBidDate().toString());
                content.append(" for the auction id: ");
                content.append(bid.getAuctionId());
                content.append(" by Buyer Id: ");
                content.append(bid.getBuyerId());
                Date timestamp = new Date();
                NotificationEvent notification = new NotificationEvent(bid.getAuctionId(),content.toString(),timestamp);
                messageKafkaTemplate.send(downStreamInQueue,"key-"+notification.getReceiverId().toString() ,notification);
            }

        }

    }
}