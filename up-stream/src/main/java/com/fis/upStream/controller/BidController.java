package com.fis.upStream.controller;


import com.fis.upStream.model.Auction;
import com.fis.upStream.model.Bid;
import com.fis.upStream.model.BidEvent;
import com.fis.upStream.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

@RequestMapping(value ="api/bids")
public class BidController {

    @Autowired
    private KafkaTemplate<String, BidEvent> kafkaTemplate;
    @Value("${upstream.topic.outQueue}")
    public String outQueue;
    @Autowired
    private BidRepository bidRepository;

    @GetMapping()
    public List<Auction> getBidId()
    {
        return bids.getAllAuctions();
    }

    @PostMapping()
    public String post(@RequestBody Bid bid) {
        bidRepository.save(bid);
        BidEvent bidEvent = new BidEvent(bid.getAuctionId(),bid.getBidPrice(),bid.getBuyerId(),bid.getBidDate());
        kafkaTemplate.send(outQueue,"key-"+ bidEvent.getAuctionId().toString(),bidEvent);
        return "Bid Placed successfully";
    }


}

