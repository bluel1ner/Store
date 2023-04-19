package com.example.userservice.service.impl;

import com.example.userservice.dto.request.ProductPhotoRequest;
import com.example.userservice.entity.mongo.Product;
import com.example.userservice.exception.type.BusinessException;
import com.example.userservice.repository.ProductRepository;
import com.example.userservice.service.ProductPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/19/2023 11:43 AM
 */
@Slf4j
@Service
public class ProductPhotoServiceImpl implements ProductPhotoService {

    private final ProductRepository productRepository;

    public ProductPhotoServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<String> getAllProductPhoto() {
        return null;
    }

    @Override
    public String addProductPhoto(MultipartFile multipartFile, ProductPhotoRequest productPhotoRequest) {
        Product product = productRepository
                .findById(productPhotoRequest.getProductId())
                .orElseThrow(() -> new BusinessException
                        (String.format("Product with id: %s not found", productPhotoRequest.getProductId()),
                                HttpStatus.NOT_FOUND));
        log.info(product.toString());
        return null;
    }

    @Override
    public void updateUserPhoto(String path, MultipartFile multipartFile) {

    }

    @Override
    public String deleteProductPhoto() {
        return null;
    }
}
