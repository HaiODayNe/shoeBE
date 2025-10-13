package com.Shoe.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetail {
    public enum Status {
        PENDING,
        ON_DELIVERY,
        DELIVERED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @OneToOne
    private Order order;

    @Column(name = "update_at")
    private Date updateAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

}
