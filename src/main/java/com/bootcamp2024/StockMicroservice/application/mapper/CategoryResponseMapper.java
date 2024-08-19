package com.bootcamp2024.StockMicroservice.application.mapper;

import com.bootcamp2024.StockMicroservice.application.dto.CategoryResponse;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryResponseMapper {


    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    CategoryResponse toResponse(Category category);


    default List<CategoryResponse> toResponseList(List<Category> categoryList){
        return categoryList.stream()
                .map(category -> {
                    CategoryResponse categoryResponse = new CategoryResponse();
                    categoryResponse.setName(category.getName());
                    categoryResponse.setDescription(category.getDescription());
                    return categoryResponse;
                }).toList();
    }

}
