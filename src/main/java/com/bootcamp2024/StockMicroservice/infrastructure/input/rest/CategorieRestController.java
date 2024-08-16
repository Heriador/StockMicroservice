package com.bootcamp2024.StockMicroservice.infrastructure.input.rest;

import com.bootcamp2024.StockMicroservice.application.dto.CategorieRequest;
import com.bootcamp2024.StockMicroservice.application.dto.CategorieResponse;
import com.bootcamp2024.StockMicroservice.application.handler.CategorieHandler;
import com.bootcamp2024.StockMicroservice.application.handler.ICategorieHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorie/")
@RequiredArgsConstructor
public class CategorieRestController {

    private final ICategorieHandler categorieHandler;

    @PostMapping
    public ResponseEntity<Void> createCategorie(@RequestBody CategorieRequest categorieRequest){
        System.out.print(categorieRequest);
        categorieHandler.createCategorie(categorieRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CategorieResponse>> getAllCategories(){
        return ResponseEntity.ok(categorieHandler.getAllcategories());
    }

    @GetMapping("/{categorieName}")
    public ResponseEntity<CategorieResponse> getCategories(@PathVariable String categorieName){
        return ResponseEntity.ok(categorieHandler.getCategorie(categorieName));
    }

    @PutMapping
    public ResponseEntity<Void> updateCategorie(@RequestBody CategorieRequest categorieRequest){
        categorieHandler.updateCategorie(categorieRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{categorieName}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable String categorieName){
        categorieHandler.deleteCategorie(categorieName);
        return  ResponseEntity.noContent().build();
    }

}
