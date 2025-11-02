package com.Shoe.service;

import com.Shoe.dto.request.adminRequest.product.BrandCreateRequest;
import com.Shoe.dto.request.adminRequest.product.CategoryCreateRequest;
import com.Shoe.model.product.Category;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface CategoryService {
    ResponseEntity<?> addCategory(CategoryCreateRequest brand);
    ResponseEntity<?> updateCategory(CategoryCreateRequest brand);
    ResponseEntity<?> deleteCategory(int id);
    ResponseEntity<?> getAllCategory();
}
