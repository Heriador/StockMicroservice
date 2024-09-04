package com.bootcamp2024.StockMicroservice.domain.usecases;

import com.bootcamp2024.StockMicroservice.domain.api.IItemServicePort;
import com.bootcamp2024.StockMicroservice.domain.exception.ItemAlreadyExistException;
import com.bootcamp2024.StockMicroservice.domain.exception.ItemNotFoundException;
import com.bootcamp2024.StockMicroservice.domain.model.Item;
import com.bootcamp2024.StockMicroservice.domain.spi.IItemPersistencePort;

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


        this.itemPersistencePort.saveItem(item);
    }

    @Override
    public Item findByName(String itemName) {
        return this.itemPersistencePort.findByName(itemName).orElseThrow(ItemNotFoundException::new);
    }

    @Override
    public Item findById(Long itemId) {
        return this.itemPersistencePort.findById(itemId).orElseThrow(ItemNotFoundException::new);
    }
}