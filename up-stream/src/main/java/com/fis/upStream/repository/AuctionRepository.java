package com.fis.upStream.repository;

import com.fis.upStream.model.Auction;
import org.springframework.data.repository.CrudRepository;

public interface AuctionRepository extends CrudRepository<Auction, String> {
}
