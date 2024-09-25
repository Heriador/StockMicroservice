package com.bootcamp2024.StockMicroservice.domain.usecases;

import com.bootcamp2024.StockMicroservice.domain.api.IItemServicePort;
import com.bootcamp2024.StockMicroservice.domain.exception.ItemAlreadyExistException;
import com.bootcamp2024.StockMicroservice.domain.exception.ItemNotFoundException;
import com.bootcamp2024.StockMicroservice.domain.exception.NoDataFoundException;
import com.bootcamp2024.StockMicroservice.domain.exception.QuantityNegativeException;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.domain.model.Item;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import com.bootcamp2024.StockMicroservice.domain.spi.IItemPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.util.DomainConstants;
import com.bootcamp2024.StockMicroservice.domain.util.ItemValidator;
import com.bootcamp2024.StockMicroservice.domain.util.PaginationValidator;

import java.util.List;

public class ItemUseCases implements IItemServicePort {

    private final IItemPersistencePort itemPersistencePort;

    public ItemUseCases(IItemPersistencePort itemPersistencePort) {
        this.itemPersistencePort = itemPersistencePort;
    }

    @Override
    public void saveItem(Item item) {

        if(this.itemPersistencePort.findByName(item.getName()).isPresent()){
            throw new ItemAlreadyExistException("Item already exist");
        }

        ItemValidator.validate(item);

        this.itemPersistencePort.saveItem(item);
    }

    @Override
    public Item findByName(String itemName) {
        return itemPersistencePort.findByName(itemName).orElseThrow(ItemNotFoundException::new);
    }

    @Override
    public Item findById(Long itemId) {
        return itemPersistencePort.findById(itemId).orElseThrow(ItemNotFoundException::new);
    }

    @Override
    public PaginationCustom<Item> getAllItems(int page, int size, String sortBy, boolean ord) {
        PaginationValidator.validate(page, size, sortBy);
        return itemPersistencePort.getAllItems(page, size, sortBy, ord).orElseThrow(NoDataFoundException::new);
    }

    @Override
    public void addStock(Long itemId, int quantity) {

        if(quantity <= 0){
            throw new QuantityNegativeException(DomainConstants.QUANTITY_NOT_POSITIVE_MESSAGE);
        }

        Item item = itemPersistencePort.findById(itemId).orElseThrow(ItemNotFoundException::new);

        itemPersistencePort.addStock(item, quantity);
    }

    @Override
    public Boolean existsById(Long itemId) {
        return itemPersistencePort.existsById(itemId);
    }

    @Override
    public Boolean hasStock(Long itemId, Long quantity) {
        return itemPersistencePort.hasStock(itemId, quantity);
    }

    @Override
    public List<String> getCategories(Long itemId) {

        Item item = itemPersistencePort.findById(itemId).orElseThrow(ItemNotFoundException::new);

          return item.getCategories().stream().map(Category::getName).toList();
    }
}
