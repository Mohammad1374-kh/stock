package com.mohammad.stock.exception;

public class StockNotFoundException extends RuntimeException{
    public StockNotFoundException(){super("exception.stock.not.found");}
}
