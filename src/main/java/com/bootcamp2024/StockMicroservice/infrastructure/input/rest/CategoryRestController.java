package com.bootcamp2024.StockMicroservice.infrastructure.input.rest;

import com.bootcamp2024.StockMicroservice.application.dto.request.AddCategory;
import com.bootcamp2024.StockMicroservice.application.dto.response.CategoryResponse;
import com.bootcamp2024.StockMicroservice.application.dto.response.PaginationResponse;
import com.bootcamp2024.StockMicroservice.application.handler.ICategoryHandler;
import com.bootcamp2024.StockMicroservice.infrastructure.input.rest.util.RolePermissionConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/category/")
@RequiredArgsConstructor
public class CategoryRestController {

    private final ICategoryHandler categoryHandler;

    @Operation(summary = "Add a new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Category already exists", content = @Content)
    })
    @PostMapping
    @PreAuthorize(RolePermissionConstants.HAS_ROLE_ADMIN)
    public ResponseEntity<Void> createCategory(@RequestBody @Valid AddCategory addCategory){
        categoryHandler.createCategory(addCategory);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get All Categories by pagination")
    @Parameters(value = {
            @Parameter(in = ParameterIn.QUERY,name = "page", description = "Page number to be returned"),
            @Parameter(in = ParameterIn.QUERY,name = "size", description = "Number of elements per page"),
            @Parameter(in =ParameterIn.QUERY, name = "ord", description = "Determines if the results should be ordered ascending(True) or descending(False)")

    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All categories returned",
                            content = @Content(mediaType = "application/json",schema = @Schema(implementation = PaginationResponse.class))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping
    public ResponseEntity<PaginationResponse<CategoryResponse>> getAllCategories(
            @RequestParam(value = "page", defaultValue = "0",required = false) int page,
            @RequestParam(value = "size", defaultValue = "10",required = false) int size,
            @RequestParam(value = "ord", defaultValue = "true", required = false) boolean ord
    ){
        PaginationResponse<CategoryResponse> response = categoryHandler.getAllcategories(page, size, ord);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get a Category by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category returned",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryResponse.class))),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })
    @GetMapping("/{categoryName}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable String categoryName){
        return ResponseEntity.ok(categoryHandler.getCategory(categoryName));
    }


}
