package com.Shoe.converter;

import com.Shoe.dto.response.adminResponse.ProductVariantAdResponse;
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

    //    public static ProductAdminResponse convertDTOAdmin(Product product) {
//        ProductAdminResponse productAdminResponse = new ProductAdminResponse();
//        productAdminResponse.setId(product.getId());
//        productAdminResponse.setName(product.getName());
//        productAdminResponse.setCategory(product.getCategory());
//        productAdminResponse.setBrand(product.getBrand());
//        List<ProductVariantAdResponse> productVariantAdResponse=product.getProductVariants()
//                .stream()
//                .map(ProductVariantAdResponse::convertToDTO)
//                .collect(Collectors.toList());
//        productAdminResponse.setProductVariants(productVariantAdResponse);
//    }
    public static ProductAdminResponse convertToAdminResponse(Product product) {
        ProductAdminResponse productAdminResponse = new ProductAdminResponse();
        productAdminResponse.setId(product.getId());
        productAdminResponse.setName(product.getName());
        productAdminResponse.setCategory(product.getCategory());
        productAdminResponse.setBrand(product.getBrand());
        // Convert danh sách variant
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
                            // Lấy thông tin kho và số lượng
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

    }
}
