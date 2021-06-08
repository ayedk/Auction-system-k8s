package com.fis.upStream.controller;


import com.fis.upStream.model.Bid;
import com.fis.upStream.model.BidEvent;
import com.fis.upStream.model.Response;
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
    public Response post(@RequestBody Bid bid) {
        if(bidService.verifBid(bid)){
            bidService.addBid(bid);
            BidEvent bidEvent = new BidEvent(bid.getAuctionId(),bid.getBidPrice(),bid.getBuyerId(),bid.getBidDate());
            kafkaTemplate.send(outQueue,"key-"+ bidEvent.getAuctionId().toString(),bidEvent);
            return new Response(200,"bid placed successfully");
        }else{
            return new Response(404,"wrong bid price");
        }
    }


}

