package com.mohammad.stock.repository;

import com.mohammad.stock.domain.entity.Stock;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepo extends ReactiveMongoRepository<Stock,String> {
}
