package com.example.userservice.service.impl;

import com.example.userservice.aws.service.PhotoStorageService;
import com.example.userservice.dto.request.ProductPhotoRequest;
import com.example.userservice.entity.mongo.Color;
import com.example.userservice.entity.mongo.Product;
import com.example.userservice.exception.type.BusinessException;
import com.example.userservice.repository.ProductRepository;
import com.example.userservice.service.ProductPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/19/2023 11:43 AM
 */
@Slf4j
@Service
public class ProductPhotoServiceImpl implements ProductPhotoService {

    private final ProductRepository productRepository;
    private final PhotoStorageService photoStorageService;

    public ProductPhotoServiceImpl(ProductRepository productRepository, PhotoStorageService photoStorageService) {
        this.productRepository = productRepository;
        this.photoStorageService = photoStorageService;
    }


    @Override
    public String getProductPhoto(String path, String name, String color, String photo) {
        return photoStorageService.getFile("%s/%s/%s/".formatted(path, name, color), photo);
    }

    @Override
    public String addProductPhoto(MultipartFile multipartFile, ProductPhotoRequest productPhotoRequest) {
        Product product = getProduct(productPhotoRequest.getProductId());
        log.info(product.toString());

        product.getColors()
                .stream()
                .filter(color -> Objects.equals(color.getName(), productPhotoRequest.getProductColor()))
                .findFirst()
                .ifPresentOrElse(color -> {
                            UUID uuid = UUID.randomUUID();
                            List<String> photoList;
                            if (Objects.isNull(color.getPhotos())) {
                                photoList = new ArrayList<>();
                            } else {
                                 photoList = color.getPhotos();
                            }
                            String path = "%s/%s/%s/%s.jpg".formatted(product.getType(), product.getName(), productPhotoRequest.getProductColor(), uuid);
                            photoList.add(path);
                            color.setPhotos(photoList);
                            productRepository.save(product);
                        },
                        () -> {
                            throw new BusinessException(String.format("Product with color: %s not found", productPhotoRequest.getProductColor()), HttpStatus.NOT_FOUND);
                        });

        return null;
//        return photoStorageService.uploadFile(path, multipartFile);
    }

    @Override
    public void updateUserPhoto(String path, MultipartFile multipartFile) {

    }

    @Override
    public String deleteProductPhoto() {
        return null;
    }


    private Product getProduct(String productId) {
        return productRepository
                .findById(productId)
                .orElseThrow(() -> new BusinessException
                        (String.format("Product with id: %s not found", productId),
                                HttpStatus.NOT_FOUND));
    }
}
