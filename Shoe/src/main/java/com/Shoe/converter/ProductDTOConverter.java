package com.Shoe.converter;

import com.Shoe.dto.response.adminResponse.ProductAdminResponse;
import com.Shoe.dto.response.adminResponse.ProductVariantAdResponse;
import com.Shoe.dto.response.customerResponse.ProductVariantCtmResponse;
import com.Shoe.model.inventory.Inventory;
import com.Shoe.model.inventory.InventoryItem;
import com.Shoe.model.product.Brand;
import com.Shoe.model.product.Category;
import com.Shoe.model.product.Product;

import com.Shoe.dto.response.customerResponse.ProductCustomerResponse;
import com.Shoe.model.product.ProductVariant;
import com.Shoe.repository.inventory.InventoryItemRepository;
import com.Shoe.repository.inventory.InventoryRepository;
import com.Shoe.repository.product.BrandRepository;
import com.Shoe.repository.product.CategoryRepository;
import com.Shoe.repository.product.ProductRepository;
import com.Shoe.repository.product.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductDTOConverter {

    @Autowired
    private static BrandRepository brandRepository;
    @Autowired
    private static CategoryRepository categoryRepository;
    @Autowired
    private static ProductRepository productRepository;
    @Autowired
    private static ProductVariantRepository productVariantRepository;
    @Autowired
    private static InventoryRepository inventoryRepository;
    @Autowired
    private static InventoryItemRepository inventoryItemRepository;

    public static ProductAdminResponse convertToAdminResponse(Product product) {
//        Optional<Brand> brand=brandRepository.findById(product.getBrandId());
//        Optional<Category> category=categoryRepository.findById(product.getCategoryId());
//        ProductAdminResponse productAdminResponse = new ProductAdminResponse();
//        productAdminResponse.setId(product.getId());
//        productAdminResponse.setName(product.getName());
//        productAdminResponse.setProductCode(product.getProductCode());
//        productAdminResponse.setCategory(category.get().getName());
//        productAdminResponse.setBrand(brand.get().getName());
////        List<ProductVariantAdResponse> variantResponses = product.getProductVariants()
////                .stream()
////                .flatMap(variant -> variant.getInventoryItems()
////                        .stream()
////                        .map(inventoryItem -> {
////                            ProductVariantAdResponse variantResponse = new ProductVariantAdResponse();
////                            variantResponse.setSize(variant.getSize());
////                            variantResponse.setPrice(variant.getPrice());
////                            variantResponse.setVersion(variant.getVersion());
////                            variantResponse.setColor(variant.getColor());
////                            variantResponse.setGender(String.valueOf(variant.getGender()));
////                            Optional<Inventory> inventoryFound=inventoryRepository.findById(inventoryItem.getInventoryId());
////                            variantResponse.setWarehouse(inventoryFound.get().getName());
////                            variantResponse.setQuantity(inventoryItem.getQuantity());
////                            return variantResponse;
////                        }))
////                .collect(Collectors.toList());
////        productAdminResponse.setProductVariants(variantResponses);
//        return productAdminResponse;
        return null;
    }


    public static ProductCustomerResponse convertDTOCustomer(Product product) {
        Optional<Brand> brand=brandRepository.findById(product.getBrandId());
        Optional<Category> category=categoryRepository.findById(product.getCategoryId());
//        return product.getProductVariants()
//                .stream()
//                .map(productVariant -> {
//                    ProductCustomerResponse productResponse = new ProductCustomerResponse();
//                    productResponse.setName(product.getName());
//                    productResponse.setProductCode(product.getProductCode());
//                    productResponse.setCategory(category.get().getName());
//                    productResponse.setBrand(brand.get().getName());
//                    productResponse.setSize(productVariant.getSize());
//                    productResponse.setPrice(productVariant.getPrice());
//                    productResponse.setVersion(productVariant.getVersion());
//                    productResponse.setColor(productVariant.getColor());
//                    productResponse.setGender(String.valueOf(productVariant.getGender()));
//                    return productResponse;
//                })
//                .collect(Collectors.toList());
        return null;
    }
}
