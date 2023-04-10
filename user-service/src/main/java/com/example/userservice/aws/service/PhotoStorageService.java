package com.example.userservice.aws.service;

import com.example.userservice.aws.enums.Path;
import org.apache.catalina.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author Neevels
 * @version 1.0
 * @date 3/24/2023 2:35 PM
 */
public interface PhotoStorageService {
    String uploadFile(Path path, String photoPath, MultipartFile file);

    String getFile(Path path, String fileName);

    String deleteFile(Path path, String fileName);

}

