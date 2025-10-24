package com.Shoe.converter;

import com.Shoe.dto.response.adminResponse.ProductVariantAdResponse;
import com.Shoe.dto.response.customerResponse.ProductVariantCtmResponse;
import com.Shoe.model.inventory.Inventory;
import com.Shoe.model.inventory.InventoryItem;
import com.Shoe.model.product.Product;
import com.Shoe.dto.response.adminRequest.ProductAdminResponse;
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


    public ProductCustomerResponse convertDTOCustomer(Product product) {
        ProductCustomerResponse productCustomerResponse = new ProductCustomerResponse();
        productCustomerResponse.setId(product.getId());
        productCustomerResponse.setName(product.getName());
        productCustomerResponse.setCategory(product.getCategory());
        productCustomerResponse.setBrand(product.getBrand());
        List<ProductVariantCtmResponse> productVariantCtmResponses = product.getProductVariants()
                .stream()
                .map(productVariant -> {
                    ProductVariantCtmResponse productVariantCtmResponse = new ProductVariantCtmResponse();
                    productVariantCtmResponse.setSize(productVariant.getSize());
                    productVariantCtmResponse.setColor(productVariant.getColor());
                    productVariantCtmResponse.setPrice(productVariant.getPrice());
                    productVariantCtmResponse.setVersion(productVariant.getVersion());
                    productVariantCtmResponse.setColor(productVariant.getColor());
                    productVariantCtmResponse.setGender(productVariant.getGender());
                    return productVariantCtmResponse;
                    })
                .collect(Collectors.toList());
        productCustomerResponse.setProductVariants(productVariantCtmResponses);
        return productCustomerResponse;
    }
}
