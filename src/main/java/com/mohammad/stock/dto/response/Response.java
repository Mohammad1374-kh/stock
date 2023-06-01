package com.mohammad.stock.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class Response<T> {
    private boolean success;
    private String message;
    private T entity;
}
