package com.bootcamp2024.StockMicroservice.infrastructure.configuration.exceptionHandler;

import com.bootcamp2024.StockMicroservice.domain.exception.EmptyFieldException;
import com.bootcamp2024.StockMicroservice.infrastructure.configuration.Constants;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.CategoryAlreadyExistsException;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.CategoryNotFoundException;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.NoDataFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestControllerAdvice
@RequiredArgsConstructor
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
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e){
        Map<String,Object> errors = new HashMap<>();

        e.getConstraintViolations().forEach(error -> {
            errors.put(error.getPropertyPath().toString(), error.getMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }


}
