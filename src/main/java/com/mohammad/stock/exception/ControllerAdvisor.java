package com.mohammad.stock.exception;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.util.Locale;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ControllerAdvisor {
    private final MessageSource messageSource;

    public ControllerAdvisor(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(StockNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    protected Mono<ResponseEntity<Object>> handleUserNotFoundException(StockNotFoundException ex,
                                                                       Locale locale) {
        return Mono.just(buildErrorResponse(ex, locale, NOT_FOUND));
    }
    @ExceptionHandler(StockSaveException.class)
    @ResponseStatus(BAD_REQUEST)
    protected Mono<ResponseEntity<Object>> handleStockSaveException(StockSaveException ex,
                                                                       Locale locale) {
        return Mono.just(buildErrorResponse(ex, locale, BAD_REQUEST));
    }

    private ResponseEntity<Object> buildErrorResponse(Exception exception,
                                                      Locale locale,
                                                      HttpStatus httpStatus) {
        String message = messageSource.getMessage(exception.getMessage(), null, locale);
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
