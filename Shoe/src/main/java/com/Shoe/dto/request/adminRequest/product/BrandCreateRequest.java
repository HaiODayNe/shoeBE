package com.Shoe.dto.request.adminRequest.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandCreateRequest {
    private String brandName;
    private String brandDescription;
}
