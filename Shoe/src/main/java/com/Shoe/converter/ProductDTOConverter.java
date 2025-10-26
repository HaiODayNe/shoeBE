package com.Shoe.converter;

import com.Shoe.dto.response.adminResponse.ProductAdminResponse;
import com.Shoe.dto.response.adminResponse.ProductVariantAdResponse;
import com.Shoe.dto.response.customerResponse.ProductVariantCtmResponse;
import com.Shoe.model.inventory.Inventory;
import com.Shoe.model.inventory.InventoryItem;
import com.Shoe.model.product.Product;

import com.Shoe.dto.response.customerResponse.ProductCustomerResponse;
import com.Shoe.model.product.ProductVariant;
import com.Shoe.repository.inventory.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDTOConverter {

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    public static ProductAdminResponse convertToAdminResponse(Product product) {
        ProductAdminResponse productAdminResponse = new ProductAdminResponse();
        productAdminResponse.setId(product.getId());
        productAdminResponse.setName(product.getName());
        productAdminResponse.setProductCode(product.getProductCode());
        productAdminResponse.setCategory(product.getCategory());
        productAdminResponse.setBrand(product.getBrand());
        List<ProductVariantAdResponse> variantResponses = product.getProductVariants()
                .stream()
                .flatMap(variant -> variant.getInventoryItems()
                        .stream()
                        .map(inventoryItem -> {
                            ProductVariantAdResponse variantResponse = new ProductVariantAdResponse();
                            variantResponse.setSize(variant.getSize());
                            variantResponse.setPrice(variant.getPrice());
                            variantResponse.setVersion(variant.getVersion());
                            variantResponse.setColor(variant.getColor());
                            variantResponse.setGender(variant.getGender());
                            Inventory inventory = inventoryItem.getWarehouse();
                            variantResponse.setWarehouse(inventory != null ? inventory.getName() : null);
                            variantResponse.setQuantity(inventoryItem.getQuantity());
                            return variantResponse;
                        }))
                .collect(Collectors.toList());
        productAdminResponse.setProductVariants(variantResponses);
        return productAdminResponse;
    }


    public static List<ProductCustomerResponse> convertDTOCustomer(Product product) {
        return product.getProductVariants()
                .stream()
                .map(productVariant -> {
                    ProductCustomerResponse productResponse = new ProductCustomerResponse();
                    productResponse.setName(product.getName());
                    productResponse.setProductCode(product.getProductCode());
                    productResponse.setCategory(product.getCategory());
                    productResponse.setBrand(product.getBrand());
                    productResponse.setSize(productVariant.getSize());
                    productResponse.setPrice(productVariant.getPrice());
                    productResponse.setVersion(productVariant.getVersion());
                    productResponse.setColor(productVariant.getColor());
                    productResponse.setGender(productVariant.getGender());
                    return productResponse;
                })
                .collect(Collectors.toList());
    }
}
