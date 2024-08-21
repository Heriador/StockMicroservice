package com.bootcamp2024.StockMicroservice.infrastructure.exception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException() {
        super("Category not found");
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
