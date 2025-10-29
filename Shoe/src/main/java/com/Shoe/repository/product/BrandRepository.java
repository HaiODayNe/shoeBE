package com.Shoe.repository.product;

import com.Shoe.model.product.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {
    Optional<Brand> findByName(Brand name);
    Optional<Brand> findById(Long id);

    Optional<Brand> findByProductId(Long brandId);
}
