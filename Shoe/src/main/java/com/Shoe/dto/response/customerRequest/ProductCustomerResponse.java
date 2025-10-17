package com.Shoe.dto.response.customerRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCustomerResponse {
    private Long id;
    private String name;
    private String category;
    private String brand;
    private String code;
    private String size;
    private String color;
    private double price;
    private String version;
    private String gender;
}
