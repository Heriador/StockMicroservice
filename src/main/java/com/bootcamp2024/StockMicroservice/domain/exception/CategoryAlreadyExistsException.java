package com.bootcamp2024.StockMicroservice.domain.exception;

public class CategoryAlreadyExistsException extends RuntimeException{
    public CategoryAlreadyExistsException() {
        super("Category already exists");
    }

    public CategoryAlreadyExistsException(String message) {
        super(message);
    }
}