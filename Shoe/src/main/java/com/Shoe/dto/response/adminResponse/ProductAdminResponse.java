package com.Shoe.dto.response.adminRequest;

import com.Shoe.enums.Gender;
import com.Shoe.enums.InventoryItemStatus;
import com.Shoe.model.product.Brand;
import com.Shoe.model.product.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductAdminResponse {
    private String id;
    private String name;
    private Category category;
    private Brand brand;
    private int size;
    private double price;
    private String version;
    private String color;
    private int quantity;
    private String warehouse;
    private InventoryItemStatus status;
    private Gender gender;
}
