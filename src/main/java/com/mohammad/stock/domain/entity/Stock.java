package com.mohammad.stock.domain.entity;

import com.mohammad.stock.domain.enums.Currency;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import static lombok.AccessLevel.PRIVATE;

@Document
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = PRIVATE)
@FieldNameConstants
@Builder
public class Stock {
    @Id
    @Getter
    private String id;
    @Getter
    @NonNull
    private String name;
    @Getter
    @NonNull
    private double price;
    @Getter
    @NonNull
    private Currency currency;

}
