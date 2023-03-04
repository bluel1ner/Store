package com.example.userservice.service.impl;

import com.example.userservice.dto.ProductDto;
import com.example.userservice.entity.Product;
import com.example.userservice.repository.ProductRepository;
import com.example.userservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public ProductDto getCompanyById(Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        return ProductDto.builder()
                .id(product.getId())
                .productType(product.getProductType())
                .name(product.getName())
                .price(product.getPrice())
                .originCountry(product.getOriginCountry())
                .amount(product.getAmount())
                .averageRate(product.getAverageRate())
                .description(product.getDescription())
                .brand(product.getBrand())
                .color(product.getColor())
                .build();
    }


}
