package com.Shoe.dto.request.adminRequest.product;

import com.Shoe.enums.Gender;
import com.Shoe.model.product.Brand;
import com.Shoe.model.product.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.Shoe.enums.InventoryItemStatus;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {
    private String name;
    private String productCode;
    private Category category;
    private Brand brand;
    private int size;
    private String productVariantCode;
    private double price;
    private String version;
    private String color;
    private Gender gender;
    private int quantity;
    private String warehouseCode;
    private InventoryItemStatus status;
}
