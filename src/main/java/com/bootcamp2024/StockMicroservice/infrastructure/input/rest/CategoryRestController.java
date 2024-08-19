package com.bootcamp2024.StockMicroservice.infrastructure.input.rest;

import com.bootcamp2024.StockMicroservice.application.dto.AddCategory;
import com.bootcamp2024.StockMicroservice.application.dto.CategoryResponse;
import com.bootcamp2024.StockMicroservice.application.handler.ICategoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category/")
@RequiredArgsConstructor
public class CategoryRestController {

    private final ICategoryHandler categoryHandler;

    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody AddCategory addCategory){
        categoryHandler.createCategory(addCategory);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories(@RequestParam Integer page,
                                                                   @RequestParam Integer size){
        return ResponseEntity.ok(categoryHandler.getAllcategories());
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<CategoryResponse> getCategories(@PathVariable String categoryName){
        return ResponseEntity.ok(categoryHandler.getCategory(categoryName));
    }


}
