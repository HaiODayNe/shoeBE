package com.Shoe.dto.response.adminResponse;

import com.Shoe.enums.Gender;
import com.Shoe.enums.InventoryItemStatus;
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
    private String name;
    private String productCode;
    private String category;
    private String brand;
    private int size;
    private String code;
    private double price;
    private String version;
    private String color;
    private int quantity;
    private List<String> warehouse;
    private List<InventoryItemStatus> status;
    private Gender gender;
}
