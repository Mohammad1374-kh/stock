package com.mohammad.stock.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mohammad.stock.domain.entity.Stock;
import com.mohammad.stock.domain.enums.Currency;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StockRequest {
    @JsonProperty("stockName")
    private String name;
    private Double price;
    private Currency currency;

    public Stock toEntity() {
        return Stock.builder()
                .name(this.name)
                .price(this.price)
                .currency(this.currency)
                .build();
    }

}
