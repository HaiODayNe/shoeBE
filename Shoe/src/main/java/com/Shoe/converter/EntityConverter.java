package com.Shoe.converter;

import com.Shoe.dto.request.adminRequest.product.ProductCreateRequest;
import com.Shoe.dto.request.adminRequest.product.ProductUpdateRequest;
import com.Shoe.model.inventory.Inventory;
import com.Shoe.model.inventory.InventoryItem;
import com.Shoe.model.product.Brand;
import com.Shoe.model.product.Category;
import com.Shoe.model.product.Product;
import com.Shoe.model.product.ProductVariant;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EntityConverter {
    public static Product convertCreateProduct(ProductCreateRequest productCreateRequest,Category category,Brand brand) {
        Product product = new Product();
        product.setName(productCreateRequest.getName());
        product.setProductCode(productCreateRequest.getProductCode());
        product.setCategoryId(category.getId());
        product.setBrandId(brand.getId());
        return product;

    }

    public static ProductVariant convertCreateProductVariant(ProductCreateRequest productCreateRequest, Optional<Product> product) {
        ProductVariant productVariant = new ProductVariant();
        productVariant.setCode(productCreateRequest.getProductVariantCode());
        productVariant.setSize(productCreateRequest.getSize());
        productVariant.setVersion(productCreateRequest.getVersion());
        productVariant.setPrice(productCreateRequest.getPrice());
        productVariant.setColor(productCreateRequest.getColor());
        productVariant.setProductId(product.get().getId());
        productVariant.setGender(productCreateRequest.getGender());
        return productVariant;
    }

    public static InventoryItem convertCreateInventoryItem(ProductCreateRequest productCreateRequest,ProductVariant  productVariant,Inventory  inventory) {
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setInventoryId(inventory.getId());
        inventoryItem.setQuantity(productCreateRequest.getQuantity());
        inventoryItem.setStatus(productCreateRequest.getStatus());
        inventoryItem.setProductVariantId(productVariant.getId());
        return inventoryItem;
    }


    public static Product convertUpdateProduct(Product product, ProductUpdateRequest productUpdateRequest,Category category,Brand brand) {
        product.setName(productUpdateRequest.getName());
        product.setProductCode(productUpdateRequest.getProductCode());
        product.setCategoryId(category.getId());
        product.setBrandId(brand.getId());
        return product;
    }

    public static ProductVariant convertUpdateProductVariant(ProductUpdateRequest productUpdateRequest,Product product) {

        ProductVariant productVariant = new ProductVariant();
        productVariant.setCode(productUpdateRequest.getProductCode());
        productVariant.setSize(productUpdateRequest.getSize());
        productVariant.setVersion(productUpdateRequest.getVersion());
        productVariant.setPrice(productUpdateRequest.getPrice());
        productVariant.setColor(productUpdateRequest.getColor());
        productVariant.setProductId(product.getId());
        return productVariant;
    }

    public static InventoryItem convertUpdateInventoryItem(ProductUpdateRequest productUpdateRequest, ProductVariant productVariant,Inventory inventory) {
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setInventoryId(inventory.getId());
        inventoryItem.setQuantity(productUpdateRequest.getQuantity());
        inventoryItem.setStatus(productUpdateRequest.getStatus());
        inventoryItem.setProductVariantId(productVariant.getId());
        return inventoryItem;
    }


}

