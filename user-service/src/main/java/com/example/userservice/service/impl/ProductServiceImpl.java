package com.example.userservice.service.impl;

import com.example.userservice.aws.service.PhotoStorageService;
import com.example.userservice.dto.mapper.ProductMapper;
import com.example.userservice.dto.request.ProductRequest;
import com.example.userservice.dto.response.ProductResponse;
import com.example.userservice.entity.mongo.Product;
import com.example.userservice.exception.type.BusinessException;
import com.example.userservice.repository.ProductRepository;
import com.example.userservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/10/2023 4:27 PM
 */
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final PhotoStorageService photoStorageService;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository, PhotoStorageService photoStorageService) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
        this.photoStorageService = photoStorageService;
    }

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        return productMapper.toResponseDto(productRepository.save(productMapper.toProduct(productRequest)));
    }

    @Override
    public void deleteProduct(String id) {
        Product product = getProduct(id);
        product.getColors()
                .forEach(color -> {
                    List<String> photos = color.getPhotos();
                    if (Objects.nonNull(photos)) {
                        photos
                                .forEach(photoStorageService::deleteFile);
                    }
                });
        productRepository.delete(getProduct(id));
    }

    @Override
    public List<Product> getProductByType(Product.Type productTypeEnum) {
        return productRepository
                .findAll()
                .stream()
                .filter(product -> product.getType()
                        .equals(productTypeEnum))
                .toList();
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository
                .findByName(name)
                .orElseThrow(
                        () -> new BusinessException(String.format("Product with name: %s not found.", name), HttpStatus.NOT_FOUND)
                );
    }


    private Product getProduct(String productId) {
        return productRepository
                .findById(productId)
                .orElseThrow(() -> new BusinessException("Product not found", HttpStatus.NOT_FOUND));
    }
}
