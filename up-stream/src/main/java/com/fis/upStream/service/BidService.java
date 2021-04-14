package com.fis.upStream.service;


import com.fis.upStream.model.Bid;
import com.fis.upStream.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BidService {

    @Autowired
    private BidRepository bidRepository;

    public List<Integer> getBuyerIds(Integer id)
    {
        List<Integer>buyerIds = new ArrayList<>();
        bidRepository.findAll().forEach(bid->
        {
            if(bid.getAuctionId()==id){
                buyerIds.add(bid.getBuyerId());
            }

        });
        return buyerIds;
    }

    public void addBid(Bid bidRecord)
    {
        bidRepository.save(bidRecord);
    }
}
