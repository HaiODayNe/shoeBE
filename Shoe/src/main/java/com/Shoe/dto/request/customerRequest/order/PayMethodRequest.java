package com.Shoe.dto.request.customerRequest.order;

import com.Shoe.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PayMethodRequest {

    private PaymentMethod paymentMethod;
}
