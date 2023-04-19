package com.example.userservice.service;

import com.example.userservice.dto.request.ProductRequest;
import com.example.userservice.dto.response.ProductResponse;
import com.example.userservice.entity.mongo.Product;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/10/2023 4:26 PM
 */
public interface ProductService {

    ProductResponse addProduct(ProductRequest product);

    Product getProduct(Integer id);

    void deleteProduct(Integer id);

    List<Product> getProductByType(Product.Type productTypeEnum);

    List<Product> getAllProduct();
}
