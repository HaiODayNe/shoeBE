package com.Shoe.dto.request.customerRequest.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
//    private List<OrderItemRequest> items;
    private CustomerInfoRequest customerInfo;
    private DiscountRequest discount;
    private PayMethodRequest payMethod;
}
