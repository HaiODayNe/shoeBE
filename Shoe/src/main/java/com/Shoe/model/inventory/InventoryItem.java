package com.Shoe.model.inventory;

import com.Shoe.enumStatus.InventoryItemStatus;
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
@Table(name = "InventoryItems")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private InventoryItemStatus status;

    @ManyToOne
    @JoinColumn(name = "variant_id",nullable = false)
    private ProductVariant productVariant;

    @ManyToOne
    @JoinColumn(name = "warehouse_id",nullable = false)
    private Inventory warehouse;
    private int quantity;


}
