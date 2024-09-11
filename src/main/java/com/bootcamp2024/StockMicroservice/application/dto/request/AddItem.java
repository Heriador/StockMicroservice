package com.bootcamp2024.StockMicroservice.application.dto.request;

import com.bootcamp2024.StockMicroservice.domain.util.DomainConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddItem {

    @NotBlank(message = DomainConstants.FIELD_NAME_BLANK_MESSAGE)
    private String name;

    @NotBlank(message = DomainConstants.FIELD_DESCRIPTION_BLANK_MESSAGE)
    private String description;

    @NotNull(message = DomainConstants.ITEM_FIELD_PRICE_NOT_NULL_MESSAGE)
    private BigDecimal price;

    @NotNull(message = DomainConstants.ITEM_FIELD_STOCK_NOT_NULL_MESSAGE)
    private Long stock;

    @NotNull(message = DomainConstants.ADDITEM_FIELD_BRANDID_NOT_NULL_MESSAGE)
    @Positive(message = DomainConstants.ADDITEM_FIELD_BRANDID_NOT_POSITIVE_MESSAGE)
    private Long brandId;

    @NotNull(message = DomainConstants.ITEM_FIELD_CATEGORIES_NOT_NULL_MESSAGE)
    @Size(min=DomainConstants.ITEM_FIELD_CATEGORIES_MIN,
          max = DomainConstants.ITEM_FIELD_CATEGORIES_MAX,
          message = DomainConstants.ITEM_FIELD_CATEGORIES_OUT_OF_RANK_MESSAGE)
    @UniqueElements(message = DomainConstants.ITEM_FIELD_CATEGORIES_NOT_UNIQUE_MESSAGE)
    @PositiveElements(message = DomainConstants.ITEM_FIELD_CATEGORIES_NOT_POSITIVE_MESSAGE)
    private List<Long> categories;

}
