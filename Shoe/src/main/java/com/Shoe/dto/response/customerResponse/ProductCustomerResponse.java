package com.Shoe.dto.response.customerRequest;

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
    private Category category;
    private Brand brand;
    private String code;
    private List<String> codes;
    private int size;
    private List<Integer> sizes;
    private String color;
    private List<String> colors;
    private double price;
    private List<Double> prices;
    private String version;
    private Gender gender;
}
