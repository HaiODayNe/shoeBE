package com.Shoe.dto.response.adminRequest;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariantResponse {
    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }

    private Long id;
    private String code;

    private int size;

    private double price;

    private String version;

    private String color;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

}
