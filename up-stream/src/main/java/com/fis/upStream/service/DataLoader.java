package com.fis.upStream.service;

import com.fis.upStream.model.Auction;
import com.fis.upStream.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataLoader implements ApplicationRunner {


    private AuctionRepository auctionRepository;

    @Autowired
    public DataLoader(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    public void run(ApplicationArguments args) {
        auctionRepository.save(new Auction(150F,"Bike", new Date(), new Date(1628722800000L),"https://bit.ly/3tKrCLL","Very nice bike with a good starting price"));
        auctionRepository.save(new Auction(150F,"Bike", new Date(), new Date(1628722800000L),"https://bit.ly/3tKrCLL","Very nice bike with a good starting price"));
        auctionRepository.save(new Auction(150F,"Bike", new Date(), new Date(1628722800000L),"https://bit.ly/3tKrCLL","Very nice bike with a good starting price"));
        auctionRepository.save(new Auction(150F,"Bike", new Date(), new Date(1628722800000L),"https://bit.ly/3tKrCLL","Very nice bike with a good starting price"));

    }
}