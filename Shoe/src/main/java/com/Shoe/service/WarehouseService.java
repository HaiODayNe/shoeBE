package com.Shoe.service;

import com.Shoe.dto.request.adminRequest.inventory.InventoryRequest;
import org.springframework.http.ResponseEntity;

public interface WarehouseService {
    ResponseEntity<?> addWarehouse(InventoryRequest  inventoryRequest);

}
