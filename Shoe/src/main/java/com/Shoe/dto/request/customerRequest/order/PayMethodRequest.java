package com.Shoe.dto.request.customerRequest.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PayMethodRequest {
    private enum paymentMethod {
        CARD,
        WALLET,
        COD
    }
    private paymentMethod paymentMethod;
}
