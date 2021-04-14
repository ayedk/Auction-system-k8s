package com.fis.upStream.controller;

import com.fis.upStream.model.Auction;
import com.fis.upStream.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/auctions")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @GetMapping()
    public List<Auction> getAllAuction()
    {
        return auctionService.getAllAuctions();
    }

    @PostMapping()
    public String postAuction(@RequestBody Auction auction)
    {
        auctionService.addAuction(auction);
        return "successfully created";
    }
}
