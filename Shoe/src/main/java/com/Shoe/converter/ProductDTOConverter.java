package com.Shoe.converter;

import com.Shoe.dto.response.adminResponse.ProductAdminResponse;
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

    public static List<ProductAdminResponse> convertToAdminResponse(Product product, Brand brand, Category category, List<ProductVariant> productVariants, List<InventoryItem> inventoryItems, List<Inventory> inventories) {
        List<ProductAdminResponse> productAdminResponses = new ArrayList<>();
            for (ProductVariant productVariant : productVariants) {
                ProductAdminResponse productAdminResponse= new ProductAdminResponse();
                productAdminResponse.setId(product.getId());
                productAdminResponse.setName(product.getName());
                productAdminResponse.setProductCode(product.getProductCode());
                productAdminResponse.setBrand(brand.getName());
                productAdminResponse.setCategory(category.getName());
                productAdminResponse.setSize(productVariant.getSize());
                productAdminResponse.setPrice(productVariant.getPrice());
                productAdminResponse.setVersion(productVariant.getVersion());
                productAdminResponse.setColor(productVariant.getColor());
                productAdminResponse.setGender(productVariant.getGender());
                productAdminResponse.setQuantity(
                        inventoryItems.stream().map(InventoryItem::getQuantity).reduce(0, Integer::sum)
                );
                productAdminResponse.setWarehouse(inventories.stream().map(Inventory::getName).collect(Collectors.toList()));
                productAdminResponses.add(productAdminResponse);
            }
        return productAdminResponses;
    }

        public static List<ProductCustomerResponse> convertDTOCustomer (Product
        product, List < ProductVariant > productVariants, Category category, Brand brand){
            List<ProductCustomerResponse> productCustomerResponses = new ArrayList<>();
                for (ProductVariant productVariant : productVariants) {
                    ProductCustomerResponse  productCustomerResponse = new ProductCustomerResponse();
                    productCustomerResponse.setName(product.getName());
                    productCustomerResponse.setProductCode(product.getProductCode());
                    productCustomerResponse.setCategory(category.getName());
                    productCustomerResponse.setBrand(brand.getName());
                    productCustomerResponse.setVersion(productVariant.getVersion());
                    productCustomerResponse.setColor(productVariant.getColor());
                    productCustomerResponse.setPrice(productVariant.getPrice());
                    productCustomerResponse.setCode(productVariant.getCode());
                    productCustomerResponse.setGender(productVariant.getGender());
                    productCustomerResponses.add(productCustomerResponse);
                }
            return productCustomerResponses;
        }
    }
