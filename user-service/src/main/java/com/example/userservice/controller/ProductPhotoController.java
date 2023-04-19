package com.example.userservice.controller;

import com.example.userservice.dto.request.ProductPhotoRequest;
import com.example.userservice.service.ProductPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/19/2023 11:44 AM
 */
@Slf4j
@RestController
@RequestMapping("/products/photo")
public class ProductPhotoController {

    private final ProductPhotoService productPhotoService;

    public ProductPhotoController(ProductPhotoService productPhotoService) {
        this.productPhotoService = productPhotoService;
    }

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestPart("request") ProductPhotoRequest productPhotoRequest) {
        log.debug("Creating new photo from dto: {}", productPhotoRequest);
        return productPhotoService.addProductPhoto(file, productPhotoRequest);
    }

    @PostMapping("/default")
    public String uploadDefaultFile(@RequestParam("file") MultipartFile file) {
        return productPhotoService.addProductPhoto(file, null);
    }

    @GetMapping()
    public ResponseEntity<List<String>> downloadImage1() {
        return ResponseEntity.ok()
                .body(productPhotoService.getAllProductPhoto());
    }

    @DeleteMapping()
    public ResponseEntity<String> deletePhoto() {
        return ResponseEntity.ok().body(productPhotoService.deleteProductPhoto());
    }
}
