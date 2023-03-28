package com.example.userservice.aws.service.impl;


import java.io.File;

import com.amazonaws.util.IOUtils;
import com.example.userservice.aws.service.PhotoStorageService;
import org.springframework.http.HttpStatus;
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
    public String uploadFile(String path, MultipartFile file) {
        String name = file.getOriginalFilename();
        try {
            File tmp = File.createTempFile("test", file.getOriginalFilename());
            file.transferTo(tmp);
            s3client.putObject(bucket, path, tmp);
        } catch (AmazonServiceException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Problem in Amazon Access" + " - " + e.getMessage());
        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return path;

    }

    @Override
    public byte[] getFile(String fileName) {
        try {
            if (!s3client.doesObjectExist(bucket, fileName)) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The file does not exist");
            } else {
//                File tmp = File.createTempFile("storeAppTmpRead", fileName);
//                FileOutputStream fos = new FileOutputStream(tmp);
                s3client.getObject(bucket, fileName);
                S3Object o = s3client.getObject(bucket, fileName);
                S3ObjectInputStream s3is = o.getObjectContent();
                byte[] bytes = IOUtils.toByteArray(s3is);
//                int read_len;
//                while ((read_len = s3is.read(bytes)) > 0) {
//                    fos.write(bytes, 0, read_len);
//                }
//                s3is.close();
//                fos.close();
                return bytes;
            }
        } catch (AmazonServiceException e) {
            //TODO: catch exception
        } catch (Exception e) {
            //TODO: catch exception
        }
        return null;

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
