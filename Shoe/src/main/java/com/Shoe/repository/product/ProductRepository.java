package com.Shoe.repository.product;

import com.Shoe.model.product.Product;
import com.Shoe.model.product.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByName(String name);

    Optional<Product> findProductByCode(String code);

}
