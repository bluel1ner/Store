package com.example.userservice.service;

import com.example.userservice.dto.response.ProductResponse;
import com.example.userservice.entity.Product;

public interface ProductService {
    void createProduct(Product product);

    ProductResponse getProductById(Integer id);
}
