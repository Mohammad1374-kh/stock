package com.mohammad.stock.controller;

import com.mohammad.stock.dto.request.StockRequest;
import com.mohammad.stock.dto.response.Response;
import com.mohammad.stock.dto.response.StockResponse;
import com.mohammad.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Locale;


@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class Controller {

    private final StockService service;
    private final MessageSource messageSource;

    //TODO: Locale is not working fine
    @GetMapping("/{id}")
    public Mono<Response<StockResponse>> getStock(@RequestParam(required = false, defaultValue = "en")
                                                  Locale locale, @PathVariable String id) {
        return service.getStock(id)
                .map(stockResponse -> Response.<StockResponse>builder()
                        .success(true)
                        .message(messageSource.getMessage("stock.found", null, locale))
                        .entity(stockResponse)
                        .build());
    }

    @GetMapping("/all")
    public Flux<Response<StockResponse>> getAll(
            @RequestParam(required = false, defaultValue = "0")
            double priceGreaterThan, Locale locale) {
        return service.getAll(priceGreaterThan)
                .map(stockResponse -> Response.<StockResponse>builder()
                        .success(true)
                        .message(messageSource.getMessage("stocks.are.fetched", null, locale))
                        .entity(stockResponse)
                        .build());
    }

    @PostMapping("/save")
    public Mono<Response<StockResponse>> save(@RequestBody StockRequest request,Locale locale) {
        return service.save(request)
                .map(stockResponse -> Response.<StockResponse>builder()
                .success(true)
                .message(messageSource.getMessage("stock.saved", null, locale))
                .entity(stockResponse)
                .build());
    }
}
