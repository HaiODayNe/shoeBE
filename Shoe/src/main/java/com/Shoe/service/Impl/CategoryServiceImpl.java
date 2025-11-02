package com.Shoe.service.Impl;

import com.Shoe.converter.CategoryDTOConverter;
import com.Shoe.dto.request.adminRequest.product.CategoryCreateRequest;
import com.Shoe.model.product.Category;
import com.Shoe.repository.product.CategoryRepository;
import com.Shoe.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public ResponseEntity<?> addCategory(CategoryCreateRequest categoryCreateRequest) {
        Optional<Category>categoryFound=categoryRepository.findByName(categoryCreateRequest.getName());
        if(categoryFound.isPresent()){
           throw new ResponseStatusException(HttpStatus.CONFLICT,"Category already exists");
        }else {
            Category category=new Category();
            CategoryDTOConverter.CategoryDTOConverter(categoryCreateRequest);
            categoryRepository.save(category);
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> updateCategory(CategoryCreateRequest categoryCreateRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteCategory(int id) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllCategory() {
        List<Category> categoryList=categoryRepository.findAll();
        return ResponseEntity.ok().body(categoryList);
    }
}
