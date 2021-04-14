package com.fis.upStream.repository;


import com.fis.upStream.model.Bid;
import org.springframework.data.repository.CrudRepository;

public interface BidRepository extends CrudRepository<Bid, String> {
}
