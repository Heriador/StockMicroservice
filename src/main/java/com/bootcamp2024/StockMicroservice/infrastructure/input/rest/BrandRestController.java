package com.bootcamp2024.StockMicroservice.infrastructure.input.rest;


import com.bootcamp2024.StockMicroservice.application.dto.AddBrand;
import com.bootcamp2024.StockMicroservice.application.dto.BrandResponse;
import com.bootcamp2024.StockMicroservice.application.handler.IBrandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brand/")
@RequiredArgsConstructor
public class BrandRestController {

    private final IBrandHandler brandHandler;


    @PostMapping
    public ResponseEntity<Void> createBrand(@RequestBody AddBrand addBrand){
        brandHandler.createBrand(addBrand);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{brandName}")
    public ResponseEntity<BrandResponse> getBrand(@PathVariable String brandName){
        return  ResponseEntity.ok(brandHandler.getBrand((brandName)));
    }

}
