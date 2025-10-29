package com.Shoe.repository.product;

import com.Shoe.model.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(Category name);
    Optional<Category> findById(Long id);

    Optional<Category> findByProductId(Long categoryId);
}
