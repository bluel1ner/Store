package com.example.userservice.service.impl;

import com.example.userservice.aws.enums.Path;
import com.example.userservice.aws.service.PhotoStorageService;
import com.example.userservice.entity.User;
import com.example.userservice.exception.type.BusinessException;
import com.example.userservice.exception.type.user.UserNotFoundException;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserPhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author Neevels
 * @version 1.0
 * @date 3/28/2023 1:15 AM
 */
@Service
public class UserPhotoServiceImpl implements UserPhotoService {
    private final PhotoStorageService photoStorageService;
    private final UserRepository userRepository;

    public UserPhotoServiceImpl(PhotoStorageService photoStorageService, UserRepository userRepository) {
        this.photoStorageService = photoStorageService;
        this.userRepository = userRepository;
    }

    @Override
    public String getUserPhoto() {
        User user = getUser();
        String photoPath = user.getPhotoPath();
        return photoStorageService.getFile(Path.USER, photoPath);
    }

    @Override
    public String addUserPhoto(MultipartFile multipartFile) {
        User user = getUser();
        String path = user.getId() + ".jpg";
        user.setPhotoPath(path);
        userRepository.save(user);
        return photoStorageService.uploadFile(Path.USER, path, multipartFile);
    }

    @Override
    public void updateUserPhoto(String path, MultipartFile multipartFile) {

    }

    @Override
    public String deleteUserPhoto() {
        User user = getUser();
        String photoPath = user.getPhotoPath();
        if (!photoPath.equals(Path.DEFAULT_PATH.getUrl())) {
            String fileName = photoStorageService.deleteFile(Path.USER, photoPath);
            user.setPhotoPath(Path.DEFAULT_PATH.getUrl());
            userRepository.save(user);
            return fileName;
        } else {
            throw new BusinessException("You cannot delete default user photo", HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public String addDefaultPhoto(MultipartFile file) {
        return photoStorageService.uploadFile(Path.USER, file.getOriginalFilename(), file);
    }


    private User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with email: %s not found", email)));
    }

}
