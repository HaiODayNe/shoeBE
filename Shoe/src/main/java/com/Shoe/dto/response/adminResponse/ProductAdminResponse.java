package com.Shoe.dto.response.adminResponse;

import com.Shoe.enums.Gender;
import com.Shoe.enums.InventoryItemStatus;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    private Integer size;
    private String code;
    private Double price;
    private String version;
    private String color;
    private Integer quantity;
    private Gender gender;
}
