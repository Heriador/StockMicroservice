package com.bootcamp2024.StockMicroservice.infrastructure.input.rest;


import com.bootcamp2024.StockMicroservice.application.dto.request.AddItem;
import com.bootcamp2024.StockMicroservice.application.dto.request.AddStock;
import com.bootcamp2024.StockMicroservice.application.dto.response.ItemResponse;
import com.bootcamp2024.StockMicroservice.application.dto.response.PaginationResponse;
import com.bootcamp2024.StockMicroservice.application.handler.IItemHandler;
import com.bootcamp2024.StockMicroservice.infrastructure.configuration.exceptionHandler.ExceptionResponse;
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

import java.util.List;

@RestController
@RequestMapping("/item/")
@RequiredArgsConstructor
public class ItemRestController {

    private final IItemHandler itemHandler;

    @Operation(summary = "Add a new Item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AddItem.class))),
            @ApiResponse(responseCode = "409", description = "Item already exists", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PreAuthorize(RolePermissionConstants.HAS_ROLE_ADMIN)
    @PostMapping
    public ResponseEntity<Void> createItem(@RequestBody @Valid AddItem addItem){
        itemHandler.createItem(addItem);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @Operation(summary = "Get a Item by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item returned",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemResponse.class))),
            @ApiResponse(responseCode = "404", description = "Item not found", content = @Content)
    })
    @GetMapping("/name/{itemName}")
    public ResponseEntity<ItemResponse> getItemByName(@PathVariable String itemName){
        return ResponseEntity.ok(itemHandler.findByName(itemName));
    }

    @Operation(summary = "Get a Item by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item returned",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemResponse.class))),
            @ApiResponse(responseCode = "404", description = "Item not found", content = @Content)
    })
    @GetMapping("/id/{itemId}")
    public ResponseEntity<ItemResponse> getItemById(@PathVariable Long itemId){
        return ResponseEntity.ok(itemHandler.findById(itemId));
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Boolean> existsById(@PathVariable Long itemId){
        return ResponseEntity.ok(itemHandler.existsById(itemId));
    }

    @GetMapping("/{itemId}/{quantity}")
    public ResponseEntity<Boolean> hasStock(@PathVariable Long itemId, @PathVariable Long quantity){
        return ResponseEntity.ok(itemHandler.hasStock(itemId, quantity));
    }

    @Operation(summary = "Get All Items by pagination")
    @Parameters(value = {
            @Parameter(in = ParameterIn.QUERY, name = "page", description = "Page number to be returned"),
            @Parameter(in = ParameterIn.QUERY, name = "size", description = "Number of elements per page"),
            @Parameter(in = ParameterIn.QUERY, name = "sortBy", description = "Parameter to sort the results"),
            @Parameter(in = ParameterIn.QUERY, name = "ord", description = "Determines if the results should be ordered ascending(True) or descending(False)")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Items returned",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = PaginationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @GetMapping
    public ResponseEntity<PaginationResponse<ItemResponse>> getAllItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1", required = false) int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "true") boolean ord){
        return ResponseEntity.ok(itemHandler.getAllItems(page, size, sortBy, ord));
    }

    @PreAuthorize(RolePermissionConstants.HAS_ROLE_WAREHOUSE_ASSISTANT)
    @PatchMapping("/stock/{itemId}")
    public ResponseEntity<Void> addStock(@PathVariable Long itemId, @RequestBody AddStock addStock){
        itemHandler.addStock(itemId, addStock);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/categories/{itemId}")
    public ResponseEntity<List<String>> getCategories(@PathVariable Long itemId){
        return ResponseEntity.ok(itemHandler.getCategories(itemId));
    }

}
