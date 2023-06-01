package com.mohammad.stock.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mohammad.stock.domain.entity.Stock;
import com.mohammad.stock.domain.enums.Currency;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StockResponse {
    private String id;
    @JsonProperty("stockName")
    private String name;
    private double price;
    private Currency currency;

    public static StockResponse fromEntity(Stock stock) {
        return StockResponse.builder()
                .id(stock.getId())
                .name(stock.getName())
                .price(stock.getPrice())
                .currency(stock.getCurrency())
                .build();
    }
}
