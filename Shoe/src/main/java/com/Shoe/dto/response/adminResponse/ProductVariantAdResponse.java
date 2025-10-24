package com.Shoe.dto.response.adminResponse;

import com.Shoe.enums.Gender;
import com.Shoe.model.product.ProductVariant;
import com.Shoe.repository.inventory.InventoryItemRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProductVariantAdResponse {

    private int size;
    private String code;
    private double price;
    private String version;
    private String color;
    private int quantity;
    private String warehouse;
    private String status;
    private Gender gender;


}

