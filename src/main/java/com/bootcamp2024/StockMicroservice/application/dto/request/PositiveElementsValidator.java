package com.bootcamp2024.StockMicroservice.application.dto.request;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class PositiveElementsValidator implements ConstraintValidator<PositiveElements, List<Long>> {

    @Override
    public boolean isValid(List<Long> value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Null values are handled by @NotNull
        }
        return value.stream().allMatch(element -> element > 0);
    }

}
