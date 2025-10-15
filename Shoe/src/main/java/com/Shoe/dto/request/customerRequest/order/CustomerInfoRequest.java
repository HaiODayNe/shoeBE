package com.Shoe.dto.request.customerRequest.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfoRequest {
    private String fullName;
    private String email;
    private String address;
    private String phoneNumber;
}
