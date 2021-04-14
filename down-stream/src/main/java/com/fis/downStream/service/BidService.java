package com.fis.downStream.service;


import com.fis.downStream.repository.BidRepository;
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
}
