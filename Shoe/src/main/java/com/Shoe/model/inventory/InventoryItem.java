package com.Shoe.model.inventory;

import com.Shoe.enums.InventoryItemStatus;
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

    private Long productVariantId;

    private Long inventoryId;

    private int quantity;


}
