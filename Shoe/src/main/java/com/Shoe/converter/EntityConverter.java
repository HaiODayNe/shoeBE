package com.Shoe.converter;

import com.Shoe.dto.request.adminRequest.product.ProductCreateRequest;
import com.Shoe.dto.request.adminRequest.product.ProductUpdateRequest;
import com.Shoe.model.inventory.Inventory;
import com.Shoe.model.inventory.InventoryItem;
import com.Shoe.model.product.Brand;
import com.Shoe.model.product.Category;
import com.Shoe.model.product.Product;
import com.Shoe.model.product.ProductVariant;
import com.Shoe.repository.inventory.InventoryItemRepository;
import com.Shoe.repository.inventory.InventoryRepository;
import com.Shoe.repository.product.BrandRepository;
import com.Shoe.repository.product.CategoryRepository;
import com.Shoe.repository.product.ProductRepository;
import com.Shoe.repository.product.ProductVariantRepository;
import com.Shoe.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class EntityConverter {
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductVariantRepository productVariantRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    public Product convertCreateProduct(ProductCreateRequest productCreateRequest) {
//        Optional<Brand> brandFound = brandRepository.findByName(productCreateRequest.getName());
//        Optional<Category> categoryFound = categoryRepository.findByName(productCreateRequest.getName());
//        Optional<Product> productFound = productRepository.findByProductCode(productCreateRequest.getProductCode());
//        Product product = new Product();
//        product.setName(productCreateRequest.getName());
//        product.setProductCode(productCreateRequest.getProductCode());
//        product.setBrandId(brandFound.get().getId());
//        product.setCategoryId(categoryFound.get().getId());
//
//        ProductVariant productVariant = new ProductVariant();
//        productVariant.setCode(productCreateRequest.getProductCode());
//        productVariant.setSize(productCreateRequest.getSize());
//        productVariant.setPrice(productCreateRequest.getPrice());
//        productVariant.setVersion(productCreateRequest.getVersion());
//        productVariant.setColor(productCreateRequest.getColor());
//        productVariant.setGender(productCreateRequest.getGender());
//        productVariant.setProductId(productFound.get().getId());
//        product.getProductVariants().add(productVariant);
//        return product;
        return null;
    }

    public Product convertUpdateProduct(ProductUpdateRequest productUpdateRequest) {
//        Optional<Brand> brandFound = brandRepository.findByName(productUpdateRequest.getName());
//        Optional<Category> categoryFound = categoryRepository.findByName(productUpdateRequest.getName());
//        Optional<Product> productFound = productRepository.findByProductCode(productUpdateRequest.getProductCode());
//        Product product = new Product();
//        product.setName(productUpdateRequest.getName());
//        product.setProductCode(productUpdateRequest.getProductCode());
//        product.setBrandId(brandFound.get().getId());
//        product.setCategoryId(categoryFound.get().getId());
//
//        return product;
        return null;
    }

    public ProductVariant convertCreateProductVariant(ProductCreateRequest productCreateRequest, Product product) {
//        Optional<Product> productFound = productRepository.findByProductCode(productCreateRequest.getProductCode());
//        ProductVariant productVariant = new ProductVariant();
//        productVariant.setCode(productCreateRequest.getProductCode());
//        productVariant.setSize(productCreateRequest.getSize());
//        productVariant.setPrice(productCreateRequest.getPrice());
//        productVariant.setVersion(productCreateRequest.getVersion());
//        productVariant.setColor(productCreateRequest.getColor());
//        productVariant.setGender(productCreateRequest.getGender());
//        productVariant.setProductId(productFound.get().getId());
////        product.getProductVariants().add(productVariant);
//        return productVariant;
        return null;
    }

    public ProductVariant convertUpdateProductVariant(ProductUpdateRequest productUpdateRequest, Product product) {
//        Optional<Product> productFound = productRepository.findByProductCode(productUpdateRequest.getProductCode());
//        ProductVariant productVariant = new ProductVariant();
//        productVariant.setCode(productUpdateRequest.getProductCode());
//        productVariant.setSize(productUpdateRequest.getSize());
//        productVariant.setPrice(productUpdateRequest.getPrice());
//        productVariant.setVersion(productUpdateRequest.getVersion());
//        productVariant.setColor(productUpdateRequest.getColor());
//        productVariant.setGender(productUpdateRequest.getGender());
//        productVariant.setProductId(productFound.get().getId());
//        product.getProductVariants().add(productVariant);
//        return productVariant;
        return null;
    }

    public InventoryItem convertCreateInventoryItem(ProductCreateRequest productCreateRequest, ProductVariant productVariant) {
//        Optional<Inventory> inventoryFound = inventoryRepository.findByWarehouseCode(productCreateRequest.getWarehouseCode());
//        Optional<ProductVariant> productVariantFound=productVariantRepository.findByCode(productCreateRequest.getProductVariantCode());
//        InventoryItem inventoryItem = new InventoryItem();
//        inventoryItem.setQuantity(productCreateRequest.getQuantity());
//        inventoryItem.setStatus(productCreateRequest.getStatus());
//        inventoryItem.setProductVariantId(productVariantFound.get().getId());
//        inventoryItem.setInventoryId(inventoryFound.get().getId());
////        productVariant.getInventoryItems().add(inventoryItem);
//        return inventoryItem;
        return null;
    }
    public InventoryItem convertUpdateInventoryItem(ProductUpdateRequest productUpdateRequest, ProductVariant productVariant) {
//        Optional<Inventory> inventoryFound = inventoryRepository.findByWarehouseCode(productUpdateRequest.getWarehouseCode());
//        Optional<ProductVariant> productVariantFound=productVariantRepository.findByCode(productUpdateRequest.getProductVariantCode());
//        InventoryItem inventoryItem = new InventoryItem();
//        inventoryItem.setQuantity(productUpdateRequest.getQuantity());
//        inventoryItem.setStatus(productUpdateRequest.getStatus());
//        inventoryItem.setProductVariantId(productVariantFound.get().getId());
//        inventoryItem.setInventoryId(inventoryFound.get().getId());
////        productVariant.getInventoryItems().add(inventoryItem);
//        return inventoryItem;
        return null;
    }


    public Inventory convertUpdateInventory(InventoryItem iventoryItem, ProductUpdateRequest productUpdateRequest) {
//        Inventory inventory = new Inventory();
//        inventory.setWarehouseCode(productUpdateRequest.getWarehouseCode());
////        inventory.getInventoryItem().add(iventoryItem);
//        return inventory;
        return null;
    }

}

