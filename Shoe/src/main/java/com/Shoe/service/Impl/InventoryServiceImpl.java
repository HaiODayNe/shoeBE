package com.Shoe.service.Impl;

import com.Shoe.model.inventory.Inventory;
import com.Shoe.model.inventory.InventoryItem;
import com.Shoe.service.InventoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Override
    public int inventoryItem(String itemName) {
        return 0;
    }

    @Override
    public Inventory updateInventory(int quantity) {
        return null;
    }

    @Override
    public Boolean checkout(int quantity) {
        return null;
    }

    @Override
    public List<InventoryItem> getStatusByName(String name) {
        return List.of();
    }

    @Override
    public List<InventoryItem> getAllItemsByWarehouse(String name) {
        return List.of();
    }
}
