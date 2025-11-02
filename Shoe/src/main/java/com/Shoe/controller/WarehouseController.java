package com.Shoe.controller;

import com.Shoe.dto.request.adminRequest.inventory.InventoryRequest;
import com.Shoe.dto.request.adminRequest.product.BrandCreateRequest;
import com.Shoe.service.BrandService;
import com.Shoe.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;
    @PostMapping("/create")
    public ResponseEntity<?> createWarehouse(@RequestBody InventoryRequest inventoryRequest){
       return warehouseService.addWarehouse(inventoryRequest);
    }
}
