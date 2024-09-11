package com.bootcamp2024.StockMicroservice.domain.usecases;

import com.bootcamp2024.StockMicroservice.domain.api.IItemServicePort;
import com.bootcamp2024.StockMicroservice.domain.exception.ItemAlreadyExistException;
import com.bootcamp2024.StockMicroservice.domain.exception.ItemNotFoundException;
import com.bootcamp2024.StockMicroservice.domain.exception.NoDataFoundException;
import com.bootcamp2024.StockMicroservice.domain.model.Item;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import com.bootcamp2024.StockMicroservice.domain.spi.IItemPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.util.ItemValidator;
import com.bootcamp2024.StockMicroservice.domain.util.PaginationValidator;

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
}
