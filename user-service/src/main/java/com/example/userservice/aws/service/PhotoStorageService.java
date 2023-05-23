package com.example.userservice.aws.service;

import com.example.userservice.aws.enums.Path;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface PhotoStorageService {
    String uploadFile(Path path, String photoPath, MultipartFile file);
    String uploadFile( String photoPath, MultipartFile file);
    File getFile(Path path, String fileName);
    File getFile(String path, String filename);
    String deleteFile(Path path, String fileName);
    String deleteFile(String fileName);
}

