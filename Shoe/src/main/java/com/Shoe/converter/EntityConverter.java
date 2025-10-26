package com.Shoe.converter;

import com.Shoe.dto.request.adminRequest.product.ProductCreateRequest;
import com.Shoe.dto.request.adminRequest.product.ProductUpdateRequest;
import com.Shoe.model.inventory.Inventory;
import com.Shoe.model.inventory.InventoryItem;
import com.Shoe.model.product.Product;
import com.Shoe.model.product.ProductVariant;
import org.springframework.stereotype.Component;

@Component
public class EntityConverter {
    public Product convertCreateProduct(ProductCreateRequest productCreateRequest) {
        Product product = new Product();
        product.setName(productCreateRequest.getName());
        product.setProductCode(productCreateRequest.getProductCode());
        product.setBrand(productCreateRequest.getBrand());
        product.setCategory(productCreateRequest.getCategory());
        return product;
    }
    public Product convertUpdateProduct(ProductUpdateRequest productUpdateRequest) {
        Product product = new Product();
        product.setName(productUpdateRequest.getName());
        product.setProductCode(productUpdateRequest.getProductCode());
        product.setBrand(productUpdateRequest.getBrand());
        product.setCategory(productUpdateRequest.getCategory());
        return product;
    }

    public ProductVariant convertCreateProductVariant(ProductCreateRequest productCreateRequest, Product product) {
        ProductVariant productVariant = new ProductVariant();
        productVariant.setCode(productCreateRequest.getProductCode());
        productVariant.setSize(productCreateRequest.getSize());
        productVariant.setPrice(productCreateRequest.getPrice());
        productVariant.setVersion(productCreateRequest.getVersion());
        productVariant.setColor(productCreateRequest.getColor());
        productVariant.setGender(productCreateRequest.getGender());
        productVariant.setProduct(product);
        product.getProductVariants().add(productVariant);
        return productVariant;
    }
    public ProductVariant convertUpdateProductVariant(ProductUpdateRequest productUpdateRequest, Product product) {
        ProductVariant productVariant = new ProductVariant();
        productVariant.setCode(productUpdateRequest.getProductCode());
        productVariant.setSize(productUpdateRequest.getSize());
        productVariant.setPrice(productUpdateRequest.getPrice());
        productVariant.setVersion(productUpdateRequest.getVersion());
        productVariant.setColor(productUpdateRequest.getColor());
        productVariant.setGender(productUpdateRequest.getGender());
        productVariant.setProduct(product);
        product.getProductVariants().add(productVariant);
        return productVariant;
    }

    public InventoryItem convertCreateInventoryItem(ProductCreateRequest productCreateRequest, ProductVariant productVariant) {
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setQuantity(productCreateRequest.getQuantity());
        inventoryItem.setStatus(productCreateRequest.getStatus());
        inventoryItem.setProductVariant(productVariant);
        return inventoryItem;
    }
    public InventoryItem convertUpdateInventoryItem(ProductUpdateRequest productUpdateRequest, ProductVariant productVariant) {
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setQuantity(productUpdateRequest.getQuantity());
        inventoryItem.setStatus(productUpdateRequest.getStatus());
        inventoryItem.setProductVariant(productVariant);
        return inventoryItem;
    }

    public Inventory convertInventory(InventoryItem iventoryItem, ProductCreateRequest productCreateRequest) {
        Inventory inventory = new Inventory();
        inventory.setWarehouseCode(productCreateRequest.getWarehouseCode());
        inventory.getInventoryItem().add(iventoryItem);
        return inventory;
    }
    public Inventory convertUpdateInventory(InventoryItem iventoryItem, ProductUpdateRequest productUpdateRequest) {
        Inventory inventory = new Inventory();
        inventory.setWarehouseCode(productUpdateRequest.getWarehouseCode());
        inventory.getInventoryItem().add(iventoryItem);
        return inventory;
    }

}

