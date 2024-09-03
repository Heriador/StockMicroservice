package com.bootcamp2024.StockMicroservice.domain.exception;

public class BrandNotFoundException extends RuntimeException {

  public BrandNotFoundException() {
      super();
  }

  public BrandNotFoundException(String message) {
        super(message);
    }
}
