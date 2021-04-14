package com.fis.downStream.repository;

import com.fis.downStream.model.Bid;
import org.springframework.data.repository.CrudRepository;

public interface BidRepository extends CrudRepository<Bid, String> {
}
