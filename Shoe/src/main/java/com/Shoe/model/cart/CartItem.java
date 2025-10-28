package com.Shoe.model.cart;

import com.Shoe.model.product.ProductVariant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productsVariantId;

    @Column(name = "quantity")
    private Integer quantity;

    private Long cartId;

    @Column(name = "total_price")
    private Double totalPrice;
}
