package com.bootcamp2024.StockMicroservice.application.dto.request;

import com.bootcamp2024.StockMicroservice.domain.util.DomainConstants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AddCategory {

    @NotEmpty(message = DomainConstants.FIELD_NAME_NULL_MESSAGE)
    @Size(max = DomainConstants.FIELD_NAME_MAX, message = DomainConstants.FIELD_NAME_MAX_EXCEEDED_MESSAGE)
    private String name;

    @NotEmpty(message = DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE)
    @Size(max = DomainConstants.FIELD_DESCRIPTION_MAX, message = DomainConstants.FIELD_DESCRIPTION_MAX_EXCEEDED_MESSAGE)
    private String description;
}
