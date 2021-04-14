package com.fis.upStream.controller;


import com.fis.upStream.model.Bid;
import com.fis.upStream.model.BidEvent;
import com.fis.upStream.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
public class BidController {

    @Autowired
    private KafkaTemplate<String, BidEvent> kafkaTemplate;

    @Value("${upstream.topic.outQueue}")
    public String outQueue;
    @Autowired
    private BidRepository bidRepository;
    @RequestMapping(value ="bid/publisher",method = RequestMethod.POST)
    public String post(@RequestBody Bid bid) {
        bidRepository.save(bid);
        BidEvent bidEvent = new BidEvent(bid.getBuyerId(),bid.getBidPrice(),bid.getAuctionId(),bid.getBidDate());
        kafkaTemplate.send(outQueue,"key-"+ bid.getAuctionId().toString(),bidEvent);
        return "Bid Placed successfully";
    }
}

