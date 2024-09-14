package com.bootcamp2024.StockMicroservice.domain.exception;

public class QuantityNegativeException extends RuntimeException {
    public QuantityNegativeException(String message) {
        super(message);
    }
}
