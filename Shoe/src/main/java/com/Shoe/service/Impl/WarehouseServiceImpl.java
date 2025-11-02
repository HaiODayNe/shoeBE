package com.Shoe.service.Impl;

import com.Shoe.converter.InventoryDTOConverter;
import com.Shoe.dto.request.adminRequest.inventory.InventoryRequest;
import com.Shoe.model.inventory.Inventory;
import com.Shoe.repository.inventory.InventoryRepository;
import com.Shoe.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    InventoryRepository inventoryRepository;

    @Override
    public ResponseEntity<?> addWarehouse(InventoryRequest inventoryRequest) {
        Optional<Inventory> inventoryFound = inventoryRepository.findByWarehouseCode(inventoryRequest.getWarehouseCode());
        if (inventoryFound.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Warehouse already exists");
        }
        Inventory inventory = InventoryDTOConverter.convertDTO(inventoryRequest);
        inventoryRepository.save(inventory);
        return ResponseEntity.ok().body(inventory);
    }

}
