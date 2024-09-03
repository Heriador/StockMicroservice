package com.bootcamp2024.StockMicroservice.domain.exception;

public class BrandAlreadyExistsException extends RuntimeException{

    public BrandAlreadyExistsException() {
        super();
    }

    public BrandAlreadyExistsException(String message) {
        super(message);
    }
}
