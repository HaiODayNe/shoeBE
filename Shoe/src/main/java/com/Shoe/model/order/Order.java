package com.Shoe.model.order;

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
    public enum Status {
        PENDING,
        PAID,
        PROCESSING,
        SHIPPED,
        DELIVERED,
        CANCELED,
        REJECTED
    }

    private enum paymentMethod {
        CARD,
        WALLET,
        COD
    }

    @Id
    @Column(name = "order_id")
    private Long orderId;

    @Column(nullable = false)
    private String orderCode;

    @OneToOne
    private Cart cart;

    @Column(nullable = false)
    private BigDecimal subtotal;
    private BigDecimal shippingFee;
    private BigDecimal discountAmount;

    @Enumerated(EnumType.STRING)
    @ManyToOne
    private CustomerInfo customerInfo;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    List<OrderDetail> orderDetails = new ArrayList<>();

    @Column(name = "order_date", nullable = false, updatable = false)
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private paymentMethod paymentMethod;

    @OneToOne
    private Discount discount;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    private String shippingAddress;

    private String notes;

    private String trackingNumber;
}
