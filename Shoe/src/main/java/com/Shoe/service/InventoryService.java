package com.Shoe.service;

import com.Shoe.model.inventory.Inventory;
import com.Shoe.model.inventory.InventoryItem;

import java.util.List;

public interface InventoryService {
    //1. theo dõi tồn kho của từng productVariant theo tên
    //2. cập nhật tồn kho khi nhập hoặc bán hàng
    //3. kiểm tra tồn trước khi cho phép checkout
    //4. quản lý/truy xuất trạng thái hàng theo ten san pham
    //5. kiểm tra hàng tại kho theo tên kho
    int inventoryItem(String itemName);

    Inventory updateInventory(int quantity);

    Boolean checkout(int quantity);

    List<InventoryItem> getStatusByName(String name);

    List<InventoryItem> getAllItemsByWarehouse(String name);
}
