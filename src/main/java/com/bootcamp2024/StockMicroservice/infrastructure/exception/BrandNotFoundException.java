package com.bootcamp2024.StockMicroservice.infrastructure.exception;

public class BrandNotFoundException extends RuntimeException {

  public BrandNotFoundException() {
  }

  public BrandNotFoundException(String message) {
        super(message);
    }
}
