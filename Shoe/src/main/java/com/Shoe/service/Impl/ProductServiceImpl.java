package com.Shoe.service.Impl;

import com.Shoe.converter.EntityConverter;
import com.Shoe.converter.ProductDTOConverter;
import com.Shoe.dto.request.adminRequest.product.ProductCreateRequest;
import com.Shoe.dto.request.adminRequest.product.ProductUpdateRequest;
import com.Shoe.dto.response.adminResponse.ProductAdminResponse;
import com.Shoe.dto.response.customerResponse.ProductCustomerResponse;
import com.Shoe.exception.ProductNotFoundException;
import com.Shoe.model.inventory.Inventory;
import com.Shoe.model.inventory.InventoryItem;
import com.Shoe.model.product.Brand;
import com.Shoe.model.product.Category;
import com.Shoe.model.product.Product;
import com.Shoe.model.product.ProductVariant;
import com.Shoe.model.user.Admin;
import com.Shoe.repository.inventory.InventoryItemRepository;
import com.Shoe.repository.inventory.InventoryRepository;
import com.Shoe.repository.product.BrandRepository;
import com.Shoe.repository.product.CategoryRepository;
import com.Shoe.repository.product.ProductRepository;
import com.Shoe.repository.product.ProductVariantRepository;
import com.Shoe.repository.user.AdminRepository;
import com.Shoe.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductVariantRepository productVariantRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private InventoryItemRepository inventoryItemRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<?> addProduct(ProductCreateRequest productCreateRequest) {
        Optional<Product> product = productRepository.findByProductCode(productCreateRequest.getProductCode());
        Optional<ProductVariant> productVariant = productVariantRepository.findProductVariantByCode(productCreateRequest.getProductVariantCode());
        Inventory inventory = inventoryRepository.findByWarehouseCode(productCreateRequest.getWarehouseCode()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Khong tim thay kho"));
        Brand brand = brandRepository.findByName(productCreateRequest.getBrand().getName()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Khong tim thay Brand"));
        Category category = categoryRepository.findByName(productCreateRequest.getCategory().getName()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Khong tim thay Category"));

        if (productVariant.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Product already exists");
        }
        ProductVariant newProductVariant;
        if (product.isPresent()) {
            newProductVariant = EntityConverter.convertCreateProductVariant(productCreateRequest, product);
            productVariantRepository.save(newProductVariant);
        } else {
            Product newProduct = EntityConverter.convertCreateProduct(productCreateRequest, category, brand);
            productRepository.save(newProduct);
            newProductVariant = EntityConverter.convertCreateProductVariant(productCreateRequest, Optional.of(newProduct));
            productVariantRepository.save(newProductVariant);
        }
        InventoryItem newInventoryItem = EntityConverter.convertCreateInventoryItem(productCreateRequest, newProductVariant, inventory);
        inventoryItemRepository.save(newInventoryItem);
        return ResponseEntity.status(HttpStatus.OK).body("Them san pham thanh cong");
    }

    @Override
    public ResponseEntity<?> getProduct(String productCode, Long userId) {
        Product productFound = productRepository.findByProductCode(productCode).orElseThrow(() -> new ProductNotFoundException("khong tim thay san pham"));
        List<ProductVariant> productVariant = productVariantRepository.findByProductId(productFound.getId());
        List<InventoryItem> inventoryItem = inventoryItemRepository.findByProductVariantIdIn(productVariant
                .stream()
                .map(variant -> variant.getId())
                .collect(Collectors.toList()));
        List<Inventory> inventory = inventoryRepository.findByIdIn(inventoryItem
                .stream()
                .map(item -> item.getInventoryId())
                .collect(Collectors.toList())
        );
        Brand brand = brandRepository.findById(productFound.getBrandId()).orElse(null);
        Category category = categoryRepository.findById(productFound.getCategoryId()).orElse(null);
        if(userId==null) {
            List<ProductCustomerResponse> productCustomerResponse = ProductDTOConverter.convertDTOCustomer(productFound, productVariant, category, brand);
            return ResponseEntity.ok(productCustomerResponse);
        }
        Optional<Admin> adminFound = adminRepository.findById(userId);
        if (adminFound.isPresent()) {
            List<ProductAdminResponse> productAdminResponse = ProductDTOConverter
                    .convertToAdminResponse(productFound, brand, category, productVariant, inventoryItem, inventory);
            return ResponseEntity.ok(productAdminResponse);
        }
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }
    @Transactional
    @Override
    public ResponseEntity<String> updateProduct(String code, ProductUpdateRequest productUpdateRequest) {
        Product productFound = productRepository.findByProductCode(code).orElseThrow(() -> new ProductNotFoundException("Khong tim thay san pham"));
        Brand brand = brandRepository.findById(productFound.getBrandId()).orElse(null);
        Category category = categoryRepository.findById(productFound.getCategoryId()).orElse(null);
        List<ProductVariant>  productVariants = productVariantRepository.findByProductId(productFound.getId());
        List<InventoryItem> inventoryItems = inventoryItemRepository.findByProductVariantIdIn(productVariants.stream().map(ProductVariant::getId).toList());
        List<Inventory> inventory = inventoryRepository.findByIdIn(inventoryItems.stream().map(InventoryItem::getInventoryId).toList());

        EntityConverter.convertUpdateProduct(productFound, productUpdateRequest, category, brand);
        productRepository.save(productFound);
        for(ProductVariant productVariant : productVariants) {
            EntityConverter.convertUpdateProductVariant(productUpdateRequest, productFound);
            productVariantRepository.save(productVariant);
        }
//      for (InventoryItem inventoryItem : inventoryItems) {
//          EntityConverter.convertUpdateInventoryItem(productUpdateRequest, productVariants, inventory);
//      }
//
//        productVariantRepository.save(productVariant);
//        inventoryItemRepository.save(inventoryItem);
        return ResponseEntity.status(HttpStatus.OK).body("Cap nhat san pham thanh cong");
    }

    @Override
    public ResponseEntity<String> deleteProduct(String code) {
        Product product = productRepository.findByProductCode(code).orElseThrow(() -> new ProductNotFoundException("Khong tim thay san pham"));
        productRepository.delete(product);
        return ResponseEntity.status(HttpStatus.OK).body("Xoa san pham thanh cong");
    }

    @Override
    public Page<ProductAdminResponse> getAllAdminProducts(int page, int pageSize, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.DESC.name())
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        if (pageSize < 1) pageSize = 1;
        if (pageSize > 5) pageSize = 5;
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductAdminResponse> productAdminResponseList = new ArrayList<>();
        for (Product product : products) {
            Brand brand = brandRepository.findById(product.getBrandId()).orElse(null);
            Category category = categoryRepository.findById(product.getCategoryId()).orElse(null);
            List<ProductVariant> productVariants = productVariantRepository.findByProductId(product.getId());
            List<InventoryItem> inventoryItems = inventoryItemRepository.findByProductVariantIdIn(productVariants
                            .stream()
                            .map(ProductVariant::getId)
                            .toList()) ;
            List<Inventory> inventories =inventoryRepository.findByIdIn(inventoryItems
                    .stream()
                    .map(InventoryItem::getId)
                    .toList());
            productAdminResponseList.addAll(
                    ProductDTOConverter.convertToAdminResponse(product, brand, category, productVariants, inventoryItems, inventories));
        }
        return new PageImpl<>(productAdminResponseList, pageable, products.getTotalElements());
    }

    @Override
    public Page<ProductCustomerResponse> getAllCtmProducts(int page, int pageSize, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.DESC.name())
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        if (pageSize < 1) pageSize = 1;
        if (pageSize > 5) pageSize = 5;
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductCustomerResponse> productCtmResponseList = new ArrayList<>();
        for (Product product : products) {
            Brand brand = brandRepository.findById(product.getBrandId()).orElse(null);
            Category category = categoryRepository.findById(product.getCategoryId()).orElse(null);
            List<ProductVariant> productVariants = productVariantRepository.findByProductId(product.getId());
            productCtmResponseList.addAll(ProductDTOConverter.convertDTOCustomer(product,productVariants,category, brand));
        }
        return new PageImpl<>(productCtmResponseList, pageable, products.getTotalElements());

    }

    @Override
    public List<Product> getProductsByCategory(int id, int page, int pageSize, String sortBy, String sortDirection) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByBrand(int id, int page, int pageSize, String sortBy, String sortDirection) {
        return List.of();
    }

    @Override
    public List<Product> checkProductStatus(String status, int page, int pageSize, String sortBy, String sortDirection) {
        return List.of();
    }
}
