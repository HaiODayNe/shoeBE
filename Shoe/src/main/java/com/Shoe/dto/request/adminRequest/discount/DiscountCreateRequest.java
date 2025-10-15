package com.Shoe.dto.request.adminRequest.discount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCreateRequest {
    private String name;
    private String code;
    private Double discount;
    private int quantity;
    private Date expiryDate;
}
