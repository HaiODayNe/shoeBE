package com.Shoe.model.product;

import com.Shoe.enums.Gender;
import com.Shoe.model.inventory.InventoryItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_variants")
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "size", nullable = false)
    private int size;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "version", nullable = false)
    private String version;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "gender", length = 20)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "productVariant", fetch = FetchType.LAZY,orphanRemoval = true)
    private List<InventoryItem> inventoryItems;


}
