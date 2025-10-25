package com.Shoe.dto.response.customerResponse;

import com.Shoe.dto.response.adminResponse.ProductVariantAdResponse;
import com.Shoe.enums.Gender;
import com.Shoe.model.product.Brand;
import com.Shoe.model.product.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCustomerResponse {
    private Long id;
    private String name;
    private String productCode;
    private Category category;
    private Brand brand;
    private  List<ProductVariantCtmResponse> productVariants;
}
