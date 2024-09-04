package com.bootcamp2024.StockMicroservice.infrastructure.input.rest;


import com.bootcamp2024.StockMicroservice.application.dto.request.AddItem;
import com.bootcamp2024.StockMicroservice.application.dto.response.ItemResponse;
import com.bootcamp2024.StockMicroservice.application.handler.IItemHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item/")
@RequiredArgsConstructor
public class ItemRestController {

    private final IItemHandler itemHandler;


    @PostMapping
    public ResponseEntity<Void> createItem(@RequestBody @Valid AddItem addItem){
        itemHandler.createItem(addItem);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/name/{itemName}")
    public ResponseEntity<ItemResponse> getItemByName(@PathVariable String itemName){
        return ResponseEntity.ok(itemHandler.findByName(itemName));
    }

    @GetMapping("/id/{itemId}")
    public ResponseEntity<ItemResponse> getItemById(@PathVariable Long itemId){
        return ResponseEntity.ok(itemHandler.findById(itemId));
    }

}
