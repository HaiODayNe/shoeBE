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
    private EntityConverter entityConverter;
    @Autowired
    private InventoryItemRepository inventoryItemRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<?> addProduct(ProductCreateRequest productCreateRequest) {
        //1. check productVariantCode: đã tồn tại thì thôi
        //2. chưa tồn tại -> check productCode
        //3. nếu đã tồn tại productCode-> thêm moi variant
        //4. chua ton tai -> them moi product
        Optional<Product> product = productRepository.findProductByCode(productCreateRequest.getProductCode());
        Optional<ProductVariant> productVariant = productVariantRepository.findProductVariantByCode(productCreateRequest.getProductVariantCode());
        Optional<InventoryItem> inventoryItem = inventoryItemRepository.findByWarehouseCode(productCreateRequest.getWarehouseCode());
        Inventory inventory = inventoryRepository.findByWarehouseCode(productCreateRequest.getWarehouseCode()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Brand brand = brandRepository.findByName(productCreateRequest.getBrand()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        Category category = categoryRepository.findByName(productCreateRequest.getCategory()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (productVariant.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Product already exists");

        } else if(product.isPresent()) {
            ProductVariant newProductVariant = EntityConverter.convertCreateProductVariant(productCreateRequest,product);
        } else {
            Product newProduct =new Product();
            ProductVariant newProductVariant = new ProductVariant();
            InventoryItem newInventoryItem = new InventoryItem();
            newProduct=EntityConverter.convertCreateProduct(productCreateRequest,category,brand);
            newProductVariant=EntityConverter.convertCreateProductVariant(productCreateRequest, Optional.of(newProduct));
            newInventoryItem=EntityConverter.convertCreateInventoryItem(productCreateRequest,newProductVariant,inventory);

        }
        return ResponseEntity.status(HttpStatus.OK).body("Them san pham thanh cong");
    }

    @Override
    public ResponseEntity<?> getProduct(String productCode, Long userId) {
        Product productFound = productRepository.findByProductCode(productCode).orElseThrow(()->new ProductNotFoundException("khong tim thay san pham"));
        List<ProductVariant> productVariant = productVariantRepository.findByProductId(productFound.getId());
        List<InventoryItem> inventoryItem = inventoryItemRepository.findByProductVariantId(productVariant
                .stream()
                .map(variant -> variant.getId())
                .collect(Collectors.toList()));
        List<Inventory> inventory = inventoryRepository.findByInventoryItemId(inventoryItem
                .stream()
                .map(item->item.getInventoryId())
                .collect(Collectors.toList())
        );
        Brand brand = brandRepository.findByProductId(productFound.getBrandId()).orElse(null);
        Category category = categoryRepository.findByProductId(productFound.getCategoryId()).orElse(null);
        Optional<Admin> adminFound = adminRepository.findById(userId);
        if (adminFound.isPresent()) {
            List<ProductAdminResponse> productAdminResponse = ProductDTOConverter
                    .convertToAdminResponse(productFound,brand,category,productVariant,inventoryItem,inventory);
            return ResponseEntity.ok(productAdminResponse);
        } else {
            List<ProductCustomerResponse> productCustomerResponse = ProductDTOConverter.convertDTOCustomer(productFound,productVariant,category,brand);
            return ResponseEntity.ok(productCustomerResponse);
        }
    }

    @Override
    public ResponseEntity<Product> updateProduct(String code, ProductUpdateRequest productUpdateRequest) {
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
        return null;
    }

    @Override
    public ResponseEntity<String> deleteProduct(String code) {
        Product product = productRepository.findProductByCode(code).orElseThrow(() -> {
            return new ProductNotFoundException("Khong tim thay san pham");
        });
        productRepository.delete(product);
        return ResponseEntity.status(HttpStatus.OK).body("Xoa san pham thanh cong");
        return null;
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
        return null;
    }

    @Override
    public Page<Product> getAllCtmProducts(int page, int pageSize, String sortBy, String sortDirection) {
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
        List<ProductCustomerResponse> productCustomerResponses = new ArrayList<>();
        for (Product product : products) {
            List<ProductCustomerResponse> productCustomerResponseList = ProductDTOConverter.convertDTOCustomer(product);
            productCustomerResponses.addAll(productCustomerResponseList);
        }
        long offset = pageable.getOffset();
        int total = productCustomerResponses.size();
        if (offset >= total) {
            return new PageImpl<>(Collections.emptyList(), pageable, total);
        }
        ;

        int endIndex = (int) Math.min(offset + pageable.getPageSize(), total);
        int startIndex = (int) offset;
        List<ProductCustomerResponse> pageCustomerResponses = productCustomerResponses.subList(startIndex, endIndex);
        return new PageImpl<>(pageCustomerResponses, pageable, total);
        return null;

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
