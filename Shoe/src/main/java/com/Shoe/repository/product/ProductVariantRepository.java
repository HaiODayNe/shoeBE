package com.Shoe.repository.product;

import com.Shoe.model.product.ProductVariant;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    Optional<ProductVariant> findByCode(String code);
    Optional<ProductVariant> findById(String name);
}
