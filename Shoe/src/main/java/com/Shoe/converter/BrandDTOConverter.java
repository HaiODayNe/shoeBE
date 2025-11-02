package com.Shoe.converter;

import com.Shoe.dto.request.adminRequest.product.BrandCreateRequest;
import com.Shoe.model.product.Brand;

public class BrandDTOConverter {
    public static Brand convertToEntity(BrandCreateRequest brand) {
        Brand brandDTO = new Brand();
        brandDTO.setName(brand.getBrandName());
        brandDTO.setDescription(brand.getBrandDescription());
        return brandDTO;
    }
}
