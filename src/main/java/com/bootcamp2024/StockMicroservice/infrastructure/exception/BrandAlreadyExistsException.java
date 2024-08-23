package com.bootcamp2024.StockMicroservice.infrastructure.exception;

public class BrandAlreadyExistsException extends RuntimeException{

    public BrandAlreadyExistsException() {
    }

    public BrandAlreadyExistsException(String message) {
        super(message);
    }
}
