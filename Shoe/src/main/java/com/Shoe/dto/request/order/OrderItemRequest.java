package com.Shoe.dto.request.order;

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
public class OrderItemRequest {
    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }
    private Long id;
    private String name;
    private Category category;
    private Brand brand;
    private int size;
    private double price;
    private String version;
    private String color;
    private Gender gender;
}
