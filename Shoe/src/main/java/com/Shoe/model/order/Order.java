package com.Shoe.model.order;

import com.Shoe.enums.OrderStatus;
import com.Shoe.enums.PaymentMethod;
import com.Shoe.model.cart.Cart;
import com.Shoe.model.customer.CustomerInfo;
import com.Shoe.model.discount.Discount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private String orderCode;

    @OneToOne
    private Cart cart;

    @Column(nullable = false)
    private BigDecimal subtotal;
    private BigDecimal shippingFee;
    private BigDecimal discountAmount;

    private Long customerInfoId;

//    List<OrderDetail> orderDetails;

    @Column(name = "order_date", nullable = false, updatable = false)
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @OneToOne
    private Discount discount;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String shippingAddress;

    private String notes;

    private String trackingNumber;
}
