package com.example.userservice.service.impl;

import com.example.userservice.aws.service.PhotoStorageService;
import com.example.userservice.entity.User;
import com.example.userservice.exception.type.user.UserNotFoundException;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserPhotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public File getUserPhoto() {
        User user = getUser();
        String photoPath = user.getPhotoPath();
        if(!Objects.nonNull(photoPath)) {
            //TODO: get default photo for all Users
            return null;
        } else {
            return photoStorageService.getFile(photoPath);
        }
    }

    @Override
    public String addUserPhoto(MultipartFile multipartFile) {
        User user = getUser();
        String path =  user.getId() + ".jpeg";
        user.setPhotoPath(path);
        userRepository.save(user);
        String fileName = photoStorageService.uploadFile("user/" +path, multipartFile);
        return fileName;
    }

    @Override
    public void updateUserPhoto(String path, MultipartFile multipartFile) {

    }

    @Override
    public void deleteUserPhoto(String path) {

    }


    private User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with email: %s not found", email)));
    }

}
