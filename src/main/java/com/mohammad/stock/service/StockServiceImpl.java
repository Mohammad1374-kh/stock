package com.mohammad.stock.service;

import com.mohammad.stock.dto.request.StockRequest;
import com.mohammad.stock.dto.response.StockResponse;
import com.mohammad.stock.exception.StockNotFoundException;
import com.mohammad.stock.exception.StockSaveException;
import com.mohammad.stock.repository.StockRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockServiceImpl implements StockService {
    private final StockRepo repo;

    @Override
    public Mono<StockResponse> getStock(String id) {
        return repo.findById(id)
                .switchIfEmpty(Mono.error(new StockNotFoundException()))
                //the below codes are for logging purposes and are not functionally required
                .doFirst(()-> log.info("Retrieving stock with id: {}",id))
                .doOnNext(stock -> log.info("stock found: {}",stock))
                .doOnError(ex-> log.error("something went wrong while fetching stock with id : {}",id,ex))
                .doOnTerminate(()-> log.info("Finalized fetching stock"))
                .doFinally(signalType -> log.info("Finalized fetching stock with signal type: {}",signalType))
                .map(StockResponse::fromEntity);
    }

    @Override
    public Flux<StockResponse> getAll(double priceGreaterThan) {
        return repo.findAll()
                .filter(stock -> stock.getPrice()> priceGreaterThan)
                .map(StockResponse::fromEntity)
                //the below codes are for logging purposes and are not functionally required
                .doFirst(()-> log.info("Retrieving all stocks"))
                .doOnNext(stock -> log.info("stock found: {}",stock))
                .doOnError(ex-> log.error("something went wrong while fetching the stocks",ex))
                .doOnTerminate(()-> log.info("Finalized fetching stock"))
                .doFinally(signalType -> log.info("Finalized fetching stock with signal type: {}",signalType));
    }

    @Override
    public Mono<StockResponse> save(StockRequest request) {

        return Mono.just(request)
                .map(StockRequest::toEntity)
                .flatMap(repo::save)
                .onErrorMap(ex-> new StockSaveException())
                .map(StockResponse::fromEntity);
    }
}
