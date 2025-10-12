package com.Shoe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_variants")
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "size", nullable = false)
    private int size;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "version", nullable = false)
    private String version;
    @Column(name = "color", nullable = false)
    private String color;
    @ManyToOne
    private Product product;
}
