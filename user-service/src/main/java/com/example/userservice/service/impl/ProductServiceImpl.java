package com.example.userservice.service.impl;

import com.example.userservice.dto.mapper.ProductMapper;
import com.example.userservice.dto.request.ProductRequest;
import com.example.userservice.dto.response.ProductResponse;
import com.example.userservice.entity.mongo.Product;
import com.example.userservice.repository.ProductRepository;
import com.example.userservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/10/2023 4:27 PM
 */
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        return productMapper.toResponseDto(productRepository.save(productMapper.toProduct(productRequest)));
    }

    @Override
    public Product getProduct(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(RuntimeException::new);

        return product;
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductByType(Product.Type productTypeEnum) {
        return productRepository
                .findAll()
                .stream()
                .filter(product -> product.getType().equals(productTypeEnum))
                .toList();
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}
