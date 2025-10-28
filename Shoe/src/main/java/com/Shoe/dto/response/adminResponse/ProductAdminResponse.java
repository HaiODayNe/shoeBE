package com.Shoe.dto.response.adminResponse;

import com.Shoe.dto.response.adminResponse.ProductVariantAdResponse;
import com.Shoe.enums.Gender;
import com.Shoe.model.product.Brand;
import com.Shoe.model.product.Category;
import com.Shoe.model.product.ProductVariant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductAdminResponse {
    private Long id;
    private String name;
    private String productCode;
    private String category;
    private String brand;
//    private  List<ProductVariantAdResponse> productVariants;
}
