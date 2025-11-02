package com.Shoe.service;

import com.Shoe.dto.request.adminRequest.product.BrandCreateRequest;
import com.Shoe.model.product.Brand;
import com.Shoe.model.product.Product;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface BrandService {
   ResponseEntity<?> addBrand(BrandCreateRequest brand);
   ResponseEntity<?> updateBrand(BrandCreateRequest brand);
   ResponseEntity<?> deleteBrand(int id);
    ResponseEntity<?> getAllBrand();
}
