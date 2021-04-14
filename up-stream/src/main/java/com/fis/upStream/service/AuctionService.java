package com.fis.upStream.service;
import com.fis.upStream.model.Auction;
import com.fis.upStream.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    public List<Auction> getAllAuctions()
    {
        List<Auction>auctionRecords = new ArrayList<>();
        auctionRepository.findAll().forEach(auctionRecords::add);
        return auctionRecords;
    }

    public void addAuction(Auction auctionRecord)
    {
        auctionRepository.save(auctionRecord);
    }
}
