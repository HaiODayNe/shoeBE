package com.Shoe.dto.response.customerResponse;

import com.Shoe.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCustomerResponse {
    private String name;
    private String productCode;
    private String category;
    private String brand;
    private int size;
    private String code;
    private double price;
    private String version;
    private String color;
    private Gender gender;
}
