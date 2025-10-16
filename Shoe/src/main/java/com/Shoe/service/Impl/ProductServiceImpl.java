package com.Shoe.service.Impl;

import com.Shoe.dto.request.adminRequest.product.ProductCreateRequest;
import com.Shoe.model.product.Product;
import com.Shoe.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProduct(int id) {
        return null;
    }

    @Override
    public Product addProduct(ProductCreateRequest productCreateRequest) {
        return null;
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
