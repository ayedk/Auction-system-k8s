package com.fis.upStream.controller;


import com.fis.upStream.model.Auction;
import com.fis.upStream.model.Bid;
import com.fis.upStream.model.BidEvent;
import com.fis.upStream.repository.BidRepository;
import com.fis.upStream.service.AuctionService;
import com.fis.upStream.service.BidService;
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
    private BidService bidService;

    @GetMapping("/auction/{id}")
    public List<Bid> getBidByAuctionId(@PathVariable(value="id") Integer id)
    {
        return bidService.getBidByAuctionId(id);
    }

    @GetMapping("/user/{id}")
    public List<Bid> getBidByUserId(@PathVariable(value="id") Integer id)
    {
        return bidService.getBidByUserId(id);
    }

    @PostMapping()
    public String post(@RequestBody Bid bid) {
        bidService.addBid(bid);
        BidEvent bidEvent = new BidEvent(bid.getAuctionId(),bid.getBidPrice(),bid.getBuyerId(),bid.getBidDate());
        kafkaTemplate.send(outQueue,"key-"+ bidEvent.getAuctionId().toString(),bidEvent);
        return "Bid Placed successfully";
    }


}

