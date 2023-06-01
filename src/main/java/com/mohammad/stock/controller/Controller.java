package com.mohammad.stock.controller;

import com.mohammad.stock.dto.request.StockRequest;
import com.mohammad.stock.dto.response.StockResponse;
import com.mohammad.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class Controller {

    private final StockService service;

    @GetMapping("/{id}")
    public Mono<StockResponse> getStock(@PathVariable String id) {
        return service.getStock(id);
    }

    @GetMapping("/all")
    public Flux<StockResponse> getAll(
            @RequestParam(required = false, defaultValue = "0")
                double priceGreaterThan){
        return service.getAll(priceGreaterThan);
    }

    @PostMapping("/save")
    public Mono<StockResponse> save(@RequestBody StockRequest request){
        return service.save(request);
    }
}
