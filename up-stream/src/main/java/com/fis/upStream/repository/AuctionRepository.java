package com.fis.upStream.repository;

import com.fis.upStream.model.Auction;
import com.fis.upStream.model.User;
import org.springframework.data.repository.CrudRepository;

public interface AuctionRepository extends CrudRepository<Auction, String> {
    Auction findById(Integer id);
}
