package com.Shoe.controller;

import com.Shoe.dto.request.adminRequest.product.ProductCreateRequest;
import com.Shoe.dto.request.adminRequest.product.ProductUpdateRequest;
import com.Shoe.dto.response.adminResponse.ProductAdminResponse;
import com.Shoe.dto.response.customerResponse.ProductCustomerResponse;
import com.Shoe.model.product.Product;
import com.Shoe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/{add}")
    public ResponseEntity<?> addProduct(@RequestBody ProductCreateRequest productCreateRequest) {
        return productService.addProduct(productCreateRequest);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getProduct(@RequestParam String productCode, @RequestParam(required = false) Long userId) {
        return productService.getProduct(productCode, userId);
    }

    @PutMapping("/{update}")
    public ResponseEntity<?> updateProduct(@RequestParam String code, @RequestBody ProductUpdateRequest productUpdateRequest) {
        return productService.updateProduct(code, productUpdateRequest);
    }

    @DeleteMapping("/{delete}")
    public ResponseEntity<String> deleteProduct(@RequestParam String code) {
        return productService.deleteProduct(code);
    }

    @GetMapping("/admin")
    public Page<ProductAdminResponse> getAllAdminProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection
    ) {
        return productService.getAllAdminProducts(page, pageSize, sortBy, sortDirection);
    }

    @GetMapping("/customer")
    public Page<ProductCustomerResponse> getAllCustomerProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection
    ) {
        return productService.getAllCtmProducts(page, pageSize, sortBy, sortDirection);
    }
}
