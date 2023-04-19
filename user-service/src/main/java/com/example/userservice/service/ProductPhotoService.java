package com.example.userservice.service;

import com.example.userservice.dto.request.ProductPhotoRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/19/2023 11:38 AM
 */
public interface ProductPhotoService {
    String getProductPhoto(String path, String name, String color, String photo);
    String addProductPhoto(MultipartFile multipartFile, ProductPhotoRequest productPhotoRequest);
    void updateUserPhoto(String path, MultipartFile multipartFile);
    String deleteProductPhoto();

}
