package com.example.userservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author Neevels
 * @version 1.0
 * @date 3/28/2023 1:12 AM
 */

public interface UserPhotoService {
    File getUserPhoto();
    String addUserPhoto(MultipartFile multipartFile);
    void updateUserPhoto(String path, MultipartFile multipartFile);
    void deleteUserPhoto(String path);
}
