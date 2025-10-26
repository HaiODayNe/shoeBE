package com.Shoe.service.Impl;

import com.Shoe.converter.EntityConverter;
import com.Shoe.converter.ProductDTOConverter;
import com.Shoe.dto.request.adminRequest.product.ProductCreateRequest;
import com.Shoe.dto.request.adminRequest.product.ProductUpdateRequest;
import com.Shoe.dto.response.adminResponse.ProductAdminResponse;
import com.Shoe.dto.response.adminResponse.ProductVariantAdResponse;
import com.Shoe.dto.response.customerResponse.ProductCustomerResponse;
import com.Shoe.exception.ProductNotFoundException;
import com.Shoe.model.inventory.Inventory;
import com.Shoe.model.inventory.InventoryItem;
import com.Shoe.model.product.Product;
import com.Shoe.model.product.ProductVariant;
import com.Shoe.model.user.Admin;
import com.Shoe.repository.inventory.InventoryItemRepository;
import com.Shoe.repository.inventory.InventoryRepository;
import com.Shoe.repository.product.ProductRepository;
import com.Shoe.repository.product.ProductVariantRepository;
import com.Shoe.repository.user.AdminRepository;
import com.Shoe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

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
    private ProductDTOConverter productDTOConverter;
    @Autowired
    private EntityConverter entityConverter;
    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Override
    public ResponseEntity<?> getProduct(Long id, Long userId) {
        Optional<Product> productFound = productRepository.findById(id);
        Optional<Admin> adminFound = adminRepository.findById(userId);
        if (adminFound.isPresent()) {
            ProductAdminResponse productAdminResponse = productDTOConverter.convertToAdminResponse(productFound.get());
            return ResponseEntity.ok(productAdminResponse);
        } else {
           List<ProductCustomerResponse> productCustomerResponse = productDTOConverter.convertDTOCustomer(productFound.get());
            return ResponseEntity.ok(productCustomerResponse);
        }
    }

    @Override
    public ResponseEntity<?> addProduct(ProductCreateRequest productCreateRequest) {
        Optional<Product> product = productRepository.findProductByCode(productCreateRequest.getProductCode());
        Optional<ProductVariant> productVariant = productVariantRepository.findProductVariantByCode(productCreateRequest.getProductVariantCode());
        Optional<Inventory> inventory = inventoryRepository.findByWarehouseCode(productCreateRequest.getWarehouseCode());
        if (product.isEmpty()) {

            Product newProduct = entityConverter.convertCreateProduct(productCreateRequest);
            productRepository.save(newProduct);

            ProductVariant newProductVariant = entityConverter.convertCreateProductVariant(productCreateRequest, newProduct);
            productVariantRepository.save(newProductVariant);

            Inventory targetInventory = inventory.orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Khong tim thay kho"));
            InventoryItem newInventoryItem = entityConverter.convertCreateInventoryItem(productCreateRequest, newProductVariant);
            newInventoryItem.setWarehouse(targetInventory);
            inventoryItemRepository.save(newInventoryItem);
            return ResponseEntity.status(HttpStatus.CREATED).body("Them san pham thanh cong");
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Product already exists");
        }
    }

    @Override
    public ResponseEntity<?> updateProduct(String code, ProductUpdateRequest productUpdateRequest) {
        Optional<Product> productFound = productRepository.findProductByCode(code);
        Product updateProduct = productFound.orElseThrow(() -> new ProductNotFoundException("Khong tim thay san pham"));
        entityConverter.convertUpdateProduct(productUpdateRequest);
        productRepository.save(updateProduct);
        ProductVariant productVariant = productVariantRepository.findProductVariantByCode(productUpdateRequest.getProductVariantCode())
                .orElseGet(() -> {
                    ProductVariant newProductVariant = entityConverter.convertUpdateProductVariant(productUpdateRequest, updateProduct);
                    newProductVariant.setProduct(updateProduct);
                    return newProductVariant;
                });
        entityConverter.convertUpdateProductVariant(productUpdateRequest, updateProduct);
        updateProduct.getProductVariants().add(productVariant);
        productVariantRepository.save(productVariant);
        InventoryItem inventoryItem = inventoryItemRepository.findByWarehouseCode(productUpdateRequest.getWarehouseCode()).orElseThrow(
                () -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Khong tim thay kho");
                }
        );
        entityConverter.convertUpdateInventoryItem(productUpdateRequest, productVariant);
        productVariant.getInventoryItems().add(inventoryItem);
        inventoryItemRepository.save(inventoryItem);
        return ResponseEntity.status(HttpStatus.OK).body("Cap nhat san pham thanh cong");
    }

    @Override
    public ResponseEntity<String> deleteProduct(String code) {
        Product product = productRepository.findProductByCode(code).orElseThrow(() -> {
            return new ProductNotFoundException("Khong tim thay san pham");
        });
        productRepository.delete(product);
        return ResponseEntity.status(HttpStatus.OK).body("Xoa san pham thanh cong");
    }

    @Override
    public Page<ProductAdminResponse> getAllAdminProducts(int page, int pageSize, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.DESC.name())
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        if (pageSize < 0) {
            pageSize = 0;
        }
        if (pageSize < 5) {
            pageSize = 5;
        }
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductAdminResponse> productAdminResponseList = new ArrayList<>();
        for (Product product : products) {
            ProductAdminResponse productAdminResponse = ProductDTOConverter.convertToAdminResponse(product);
            productAdminResponseList.add(productAdminResponse);
        }
        return new PageImpl<>(productAdminResponseList, pageable, products.getTotalElements());
    }

    @Override
    public Page<?> getAllCtmProducts(int page, int pageSize, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.DESC.name())
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        if (pageSize < 0) {
            pageSize = 0;
        }
        if (pageSize > 5) {
            pageSize = 5;
        }
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Pageable getProductPage = PageRequest.of(0, 5, sort);
        Page<Product> products = productRepository.findAll(getProductPage);
        List<ProductCustomerResponse> productCustomerResponses=new ArrayList<>();
        for(Product product : products) {
            List<ProductCustomerResponse> productCustomerResponseList = ProductDTOConverter.convertDTOCustomer(product);
            productCustomerResponses.addAll(productCustomerResponseList);
        }
        long offset= pageable.getOffset();
        int total=productCustomerResponses.size();
        if(offset>=total){
            return new PageImpl<>(Collections.emptyList(), pageable, total);
        };

        int endIndex=(int) Math.min(offset+pageable.getPageSize(),total);
        int startIndex=(int)offset;
        List<ProductCustomerResponse> pageCustomerResponses=productCustomerResponses.subList(startIndex,endIndex);
        return new PageImpl<>(pageCustomerResponses,pageable,total);

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
