package com.example.userservice.service;

import com.example.userservice.dto.ProductDto;
import com.example.userservice.entity.Product;

public interface ProductService {
    void createProduct(Product product);

    ProductDto getCompanyById(Integer id);
}
