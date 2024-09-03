package com.bootcamp2024.StockMicroservice.infrastructure.input.rest;


import com.bootcamp2024.StockMicroservice.application.dto.request.AddBrand;
import com.bootcamp2024.StockMicroservice.application.dto.response.BrandResponse;
import com.bootcamp2024.StockMicroservice.application.handler.IBrandHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brand/")
@RequiredArgsConstructor
public class BrandRestController {

    private final IBrandHandler brandHandler;


    @Operation(summary = "Add a new Brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AddBrand.class))),
            @ApiResponse(responseCode = "409", description = "Brand already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> createBrand(@RequestBody @Valid AddBrand addBrand){
        brandHandler.createBrand(addBrand);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get a Brand by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brand returned",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BrandResponse.class))),
            @ApiResponse(responseCode = "404", description = "Brand not found", content = @Content)
    })
    @GetMapping("/name/{brandName}")
    public ResponseEntity<BrandResponse> getBrandByName(@PathVariable String brandName){
        return  ResponseEntity.ok(brandHandler.findByName((brandName)));
    }

    @Operation(summary = "Get a Brand by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brand returned",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BrandResponse.class))),
            @ApiResponse(responseCode = "404", description = "Brand not found", content = @Content)
    })
    @GetMapping("/id/{brandId}")
    public ResponseEntity<BrandResponse> getBrandById(@PathVariable Long brandId){
        return  ResponseEntity.ok(brandHandler.findById((brandId)));
    }



}
