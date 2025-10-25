package com.Shoe.service.Impl;

import com.Shoe.converter.EntityConverter;
import com.Shoe.converter.ProductDTOConverter;
import com.Shoe.dto.request.adminRequest.product.ProductCreateRequest;
import com.Shoe.dto.response.adminResponse.ProductAdminResponse;
import com.Shoe.dto.response.adminResponse.ProductVariantAdResponse;
import com.Shoe.dto.response.customerResponse.ProductCustomerResponse;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
            ProductCustomerResponse productCustomerResponse = productDTOConverter.convertDTOCustomer(productFound.get());
            return ResponseEntity.ok(productCustomerResponse);
        }
    }

    @Override
    public ResponseEntity<?> addProduct(ProductCreateRequest productCreateRequest) {
        Optional<Product> product = productRepository.findProductByCode(productCreateRequest.getProductCode());
        Optional<ProductVariant> productVariant = productVariantRepository.findProductVariantByCode(productCreateRequest.getProductVariantCode());
        Optional<Inventory> inventory = inventoryRepository.findByWarehouseCode(productCreateRequest.getWarehouseCode());
        if (product.isEmpty()) {

            Product newProduct = entityConverter.convertProduct(productCreateRequest);
            productRepository.save(newProduct);

            ProductVariant newProductVariant = entityConverter.convertProductVariant(productCreateRequest, newProduct);
            productVariantRepository.save(newProductVariant);

            Inventory targetInventory=inventory.orElseThrow(
                    ()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Khong tim thay kho"));
           InventoryItem newInventoryItem=entityConverter.covertInventoryItem(productCreateRequest,newProductVariant);
            newInventoryItem.setWarehouse(targetInventory);
            inventoryItemRepository.save(newInventoryItem);
            return ResponseEntity.status(HttpStatus.CREATED).body("Them san pham thanh cong");
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Product already exists");
        }
    }

    @Override
    public Product updateProduct(int id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(int id) {

    }

    @Override
    public List<Product> getAllProducts(int page, int pageSize, String sortBy, String sortDirection) {
        return List.of();
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
