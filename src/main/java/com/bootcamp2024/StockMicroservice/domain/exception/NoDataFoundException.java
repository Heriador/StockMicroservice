package com.bootcamp2024.StockMicroservice.domain.exception;

public class NoDataFoundException extends RuntimeException{

    public NoDataFoundException(){super();}

    public NoDataFoundException(String message) {
        super(message);
    }
}
