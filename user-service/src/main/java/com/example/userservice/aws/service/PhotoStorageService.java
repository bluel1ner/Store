package com.example.userservice.aws.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Neevels
 * @version 1.0
 * @date 3/24/2023 2:35 PM
 */
public interface PhotoStorageService {
    ResponseEntity<String> uploadFile(MultipartFile file);

    ResponseEntity<?> getFile(String fileName);

    ResponseEntity<String> deleteFile(String fileName);
}
