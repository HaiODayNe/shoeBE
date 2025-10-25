package com.Shoe.service;

import com.Shoe.dto.request.adminRequest.product.ProductCreateRequest;
import com.Shoe.model.inventory.InventoryItem;
import com.Shoe.model.product.Category;
import com.Shoe.model.product.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    ResponseEntity<?>  getProduct(Long id,Long userId);

    ResponseEntity<?> addProduct(ProductCreateRequest productCreateRequest);

    Product updateProduct(int id, Product product);

    void deleteProduct(int id);

    List<Product> getAllProducts(int page, int pageSize, String sortBy, String sortDirection);

    List<Product> getProductsByCategory(int id, int page, int pageSize, String sortBy, String sortDirection);

    List<Product> getProductsByBrand(int id, int page, int pageSize, String sortBy, String sortDirection);

    List<Product> checkProductStatus(String status, int page, int pageSize, String sortBy, String sortDirection);

}
