package com.bootcamp2024.StockMicroservice.application.dto.request;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PositiveElementsValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PositiveElements {
    String message() default "List contains non-positive elements";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}