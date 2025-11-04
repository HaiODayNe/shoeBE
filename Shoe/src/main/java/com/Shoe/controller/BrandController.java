package com.Shoe.controller;

import com.Shoe.dto.request.adminRequest.product.BrandCreateRequest;
import com.Shoe.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;
    @PostMapping("/create")
    public ResponseEntity<?> createBrand(@RequestBody BrandCreateRequest brandCreateRequest){
        return brandService.addBrand(brandCreateRequest);
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllBrand(){
        return brandService.getAllBrand();
    }
}
