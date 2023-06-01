package com.mohammad.stock.service;

import com.mohammad.stock.dto.request.StockRequest;
import com.mohammad.stock.dto.response.StockResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface StockService {
    Mono<StockResponse> getStock(String id);

    Flux<StockResponse> getAll();

    Mono<StockResponse> save(StockRequest request);
}
