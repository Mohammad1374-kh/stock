package com.mohammad.stock.service;

import com.mohammad.stock.dto.request.StockRequest;
import com.mohammad.stock.dto.response.StockResponse;
import com.mohammad.stock.repository.StockRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepo repo;

    @Override
    public Mono<StockResponse> getStock(String id) {
        return repo.findById(id)
                .map(StockResponse::fromEntity);
    }

    @Override
    public Flux<StockResponse> getAll() {
        return repo.findAll()
                .map(StockResponse::fromEntity);
    }

    @Override
    public Mono<StockResponse> save(StockRequest request) {
        return repo.save(request.toEntity())
                .map(StockResponse::fromEntity);
    }
}
