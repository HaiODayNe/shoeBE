package com.Shoe.converter;

import com.Shoe.dto.request.adminRequest.inventory.InventoryRequest;
import com.Shoe.model.inventory.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryDTOConverter {
    public static Inventory convertDTO(InventoryRequest inventoryRequest) {
        Inventory inventory = new Inventory();
        inventory.setName(inventoryRequest.getName());
        inventory.setWarehouseCode(inventoryRequest.getWarehouseCode());
        inventory.setAddress(inventoryRequest.getAddress());
        inventory.setLocation(inventoryRequest.getLocation());
        return inventory;
    }
}
