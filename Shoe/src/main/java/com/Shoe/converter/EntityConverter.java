package com.Shoe.converter;

import com.Shoe.dto.request.adminRequest.product.ProductCreateRequest;
import com.Shoe.model.inventory.Inventory;
import com.Shoe.model.inventory.InventoryItem;
import com.Shoe.model.product.Product;
import com.Shoe.model.product.ProductVariant;
import org.springframework.stereotype.Component;

@Component
public class EntityConverter {
    public Product convertProduct(ProductCreateRequest productCreateRequest) {
        Product product = new Product();
        product.setName(productCreateRequest.getName());
        product.setProductCode(productCreateRequest.getProductCode());
        product.setBrand(productCreateRequest.getBrand());
        product.setCategory(productCreateRequest.getCategory());
        return product;
    }


    public ProductVariant convertProductVariant(ProductCreateRequest productCreateRequest, Product product) {
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


    public InventoryItem covertInventoryItem(ProductCreateRequest productCreateRequest, ProductVariant productVariant) {
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setQuantity(productCreateRequest.getQuantity());
        inventoryItem.setStatus(productCreateRequest.getStatus());
        inventoryItem.setProductVariant(productVariant);
        return inventoryItem;
    }


    public Inventory convertInventory(InventoryItem iventoryItem, ProductCreateRequest productCreateRequest) {
        Inventory inventory = new Inventory();
        inventory.setWarehouseCode(productCreateRequest.getWarehouseCode());
        inventory.getInventoryItem().add(iventoryItem);
        return inventory;
    }

}

