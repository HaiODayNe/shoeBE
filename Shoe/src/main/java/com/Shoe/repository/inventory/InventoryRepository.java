package com.Shoe.repository.inventory;

import com.Shoe.model.inventory.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByWarehouseCode(String warehouseCode);
    Optional<Inventory> findById(Long id);
    List<Inventory> findByIdIn(List<Long> ids);
}
