package com.Shoe.converter;

import com.Shoe.dto.request.adminRequest.product.CategoryCreateRequest;
import com.Shoe.model.product.Category;

public class CategoryDTOConverter {
    public static Category CategoryDTOConverter(CategoryCreateRequest categoryCreateRequest) {
        Category category = new Category();
        category.setName(categoryCreateRequest.getName());
        return category;
    }
}
