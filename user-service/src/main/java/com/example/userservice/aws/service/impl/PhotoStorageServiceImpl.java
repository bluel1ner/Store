package com.example.userservice.aws.service.impl;


import java.io.File;

import com.amazonaws.util.IOUtils;
import com.example.userservice.aws.service.PhotoStorageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

/**
 * @author Neevels
 * @version 1.0
 * @date 3/24/2023 2:36 PM
 */
@Service
public class PhotoStorageServiceImpl implements PhotoStorageService {

    private final AmazonS3 s3client;
    private final String bucket = "test-backet-ilya";

    public PhotoStorageServiceImpl(AmazonS3 s3client) {
        this.s3client = s3client;
    }


    @Override
    public ResponseEntity<String> uploadFile(MultipartFile file) {
        try {
            File tmp = File.createTempFile("test", file.getOriginalFilename());
            file.transferTo(tmp);
            String path = file.getOriginalFilename();
            s3client.putObject(bucket, path, tmp);
            return ResponseEntity.status(HttpStatus.OK).body(file.getOriginalFilename());
        } catch (AmazonServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Problem in Amazon Access" + " - " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @Override
    public ResponseEntity<?> getFile(String fileName) {
        try {
            if (!s3client.doesObjectExist(bucket, fileName)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The file does not exist");
            } else {
                s3client.getObject(bucket, fileName);
                S3Object o = s3client.getObject(bucket, fileName);
                S3ObjectInputStream s3is = o.getObjectContent();
                byte[] bytes = IOUtils.toByteArray(s3is);
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                httpHeaders.setContentLength(bytes.length);
                httpHeaders.setContentDispositionFormData("attachment", fileName);
                return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
            }
        } catch (AmazonServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Problem in Amazon Access" + " - " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> deleteFile(String fileName) {
        try {
            s3client.deleteObject(bucket, fileName);
            return ResponseEntity.status(HttpStatus.OK).body(fileName);
        } catch (AmazonServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Problem in Amazon Access" + " - " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
