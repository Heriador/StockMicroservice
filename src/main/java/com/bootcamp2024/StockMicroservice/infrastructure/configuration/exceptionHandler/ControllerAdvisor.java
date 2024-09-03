package com.bootcamp2024.StockMicroservice.infrastructure.configuration.exceptionHandler;

import com.bootcamp2024.StockMicroservice.domain.exception.EmptyFieldException;
import com.bootcamp2024.StockMicroservice.infrastructure.configuration.Constants;
import com.bootcamp2024.StockMicroservice.domain.exception.CategoryAlreadyExistsException;
import com.bootcamp2024.StockMicroservice.domain.exception.CategoryNotFoundException;
import com.bootcamp2024.StockMicroservice.domain.exception.NoDataFoundException;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.*;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyFieldException(EmptyFieldException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(String.format(Constants.EMPTY_FIELD_EXCEPTION_MESSAGE,e.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoDataFoundException(NoDataFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(String.format(Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE,e.getMessage()), HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public  ResponseEntity<ExceptionResponse> handleCategorieAlreadyExistsException(CategoryAlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(String.format(Constants.CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE,e.getMessage()), HttpStatus.CONFLICT.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public  ResponseEntity<ExceptionResponse> handleCategorieNotFoundException(CategoryNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(String.format(Constants.CATEGORY_NOT_FOUND_EXCEPTION_MESSAGE,e.getMessage()), HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(BrandAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> hanldeBrandAlreadyExistsException(BrandNotFoundException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(String.format(Constants.BRAND_ALREADY_EXISTS_EXCEPTION_MESSAGE, e.getMessage()),HttpStatus.CONFLICT.toString(),LocalDateTime.now()));
    }

    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleBrandNotFoundException(BrandNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(String.format(Constants.BRAND_NOT_FOUND_EXCEPTION_MESSAGE, e.getMessage()), HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(),error.getDefaultMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
