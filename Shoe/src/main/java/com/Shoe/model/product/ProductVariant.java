package com.Shoe.model.product;

import com.Shoe.dto.request.adminRequest.product.ProductCreateRequest;
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

    @Column(name="code",unique = true)
    private String code;

    private Long productId;

    @Column(name = "size", nullable = false)
    private int size;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "version", nullable = false)
    private String version;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

//    private List<InventoryItem> inventoryItems;

}
