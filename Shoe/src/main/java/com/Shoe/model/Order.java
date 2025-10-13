package com.Shoe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @Column(name = "order_id")
    private Integer orderId;

    @OneToOne
    private Cart cart;

    @ManyToOne
    private CustomerInfo customerInfo;

    @Column(name = "order_date", nullable = false, updatable = false)
    private Date orderDate;

    @Column(name="payment",nullable = false)
    private String payment;

    @OneToOne
    private Discount discount;
}
