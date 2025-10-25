package com.Shoe.model.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name="product_code", nullable = false, unique = true)
    private String productCode;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Column(name = "updateAt", nullable = false)
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<ProductVariant> productVariants = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.updateAt = LocalDateTime.now();
    }

}