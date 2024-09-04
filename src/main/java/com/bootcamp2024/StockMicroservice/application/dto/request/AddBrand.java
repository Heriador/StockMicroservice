package com.bootcamp2024.StockMicroservice.application.dto.request;


import com.bootcamp2024.StockMicroservice.domain.util.DomainConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddBrand {

    @NotBlank(message = DomainConstants.FIELD_NAME_BLANK_MESSAGE)
    @Size(max = DomainConstants.FIELD_NAME_MAX, message = DomainConstants.FIELD_NAME_MAX_EXCEEDED_MESSAGE)
    private String name;

    @NotBlank(message = DomainConstants.FIELD_DESCRIPTION_BLANK_MESSAGE)
    @Size(max = DomainConstants.BRAND_FIELD_DESCRIPTION_MAX, message = DomainConstants.BRAND_FIELD_DESCRIPTION_MAX_EXCEEDED_MESSAGE)
    private String description;
}
