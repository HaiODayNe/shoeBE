package com.Shoe.service;

import com.Shoe.dto.request.adminRequest.product.ProductCreateRequest;
import com.Shoe.dto.request.adminRequest.product.ProductUpdateRequest;
import com.Shoe.dto.response.adminResponse.ProductAdminResponse;
import com.Shoe.dto.response.customerResponse.ProductCustomerResponse;
import com.Shoe.model.inventory.InventoryItem;
import com.Shoe.model.product.Category;
import com.Shoe.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    ResponseEntity<?>  getProduct(Long id,Long userId);

    ResponseEntity<?> addProduct(ProductCreateRequest productCreateRequest);

    ResponseEntity<?> updateProduct(String code, ProductUpdateRequest productUpdateRequest);

    ResponseEntity<String> deleteProduct(String code);

    List<ProductAdminResponse> getAllAdminProducts();
    List<ProductCustomerResponse> getAllCtmProducts();
    List<Product> getProductsByCategory(int id, int page, int pageSize, String sortBy, String sortDirection);

    List<Product> getProductsByBrand(int id, int page, int pageSize, String sortBy, String sortDirection);

    List<Product> checkProductStatus(String status, int page, int pageSize, String sortBy, String sortDirection);

}
