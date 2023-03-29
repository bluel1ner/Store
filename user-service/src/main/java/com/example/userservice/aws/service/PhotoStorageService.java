package com.example.userservice.aws.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author Neevels
 * @version 1.0
 * @date 3/24/2023 2:35 PM
 */
public interface PhotoStorageService {
    String uploadFile(String path, MultipartFile file);

    File getFile(String fileName);

    ResponseEntity<String> deleteFile(String fileName);
}
