package com.example.userservice.aws;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Neevels
 * @version 1.0
 * @date 3/24/2023 2:37 PM
 */
@RestController
@RequestMapping("/aws")
public class AwsController {
    private final PhotoStorageService photoStorageService;

    public AwsController(PhotoStorageService photoStorageService) {
        this.photoStorageService = photoStorageService;
    }


    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        return photoStorageService.uploadFile(file);
    }

    @GetMapping("{fileName}")
    public ResponseEntity<FileSystemResource> downloadImage(@PathVariable String fileName) {
        return (ResponseEntity<FileSystemResource>) photoStorageService.getFile(fileName);
    }

    @DeleteMapping("{fileName}")
    public ResponseEntity<?> deletePhoto(@PathVariable String fileName) {
        return photoStorageService.deleteFile(fileName);
    }
}
