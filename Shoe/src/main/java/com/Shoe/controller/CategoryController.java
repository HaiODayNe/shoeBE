package com.Shoe.controller;

import com.Shoe.dto.request.adminRequest.product.CategoryCreateRequest;
import com.Shoe.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/create")
    public ResponseEntity<?> addCategory(@RequestBody CategoryCreateRequest categoryCreateRequest){
        return categoryService.addCategory(categoryCreateRequest);
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        return categoryService.getAllCategory();
    }
}
