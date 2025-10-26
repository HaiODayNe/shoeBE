package com.Shoe.repository.inventory;

import com.Shoe.model.inventory.Inventory;
import com.Shoe.model.inventory.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem,Long> {
//   @Query("SELECT iv.quantity from inventory_item iv " +
//           "join product_variants pv on iv.variant_id=pv.id " +
//           "join product p on pv.product_id=p.id where p.id=?")
    @Query("select i from InventoryItem i where i.productVariant.product.id=:productId")
   List<InventoryItem> getQuantityByProductId(@Param("productId") Long productId);
Optional<InventoryItem> findByWarehouseCode(String warehouseCode);
}
