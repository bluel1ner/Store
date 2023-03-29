package com.example.userservice.controller;

import com.example.userservice.aws.service.PhotoStorageService;
import com.example.userservice.service.UserPhotoService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author Neevels
 * @version 1.0
 * @date 3/28/2023 1:11 AM
 */
@RestController
@RequestMapping("/users/photo")
public class UserPhotoController {
    private final UserPhotoService userPhotoService;


    public UserPhotoController(UserPhotoService userPhotoService) {
        this.userPhotoService = userPhotoService;
    }


    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return userPhotoService.addUserPhoto(file);
    }

//    @GetMapping("{fileName}")
    @GetMapping()
    public File downloadImage() {
        return  userPhotoService.getUserPhoto();
    }
//
//    @DeleteMapping("{fileName}")
//    public ResponseEntity<?> deletePhoto(@PathVariable String fileName) {
//        return userPhotoService.deleteFile(fileName);
//    }
}
