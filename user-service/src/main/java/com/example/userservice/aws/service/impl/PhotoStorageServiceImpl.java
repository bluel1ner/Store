package com.example.userservice.aws.service.impl;


import java.io.File;
import java.io.FileOutputStream;
import com.amazonaws.util.IOUtils;
import com.example.userservice.aws.enums.Path;
import com.example.userservice.aws.service.PhotoStorageService;
import com.example.userservice.exception.type.BusinessException;
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
    public String uploadFile(Path path, String photoPath, MultipartFile file) {
        try {
            File tmp = File.createTempFile("test", file.getOriginalFilename());
            file.transferTo(tmp);
            s3client.putObject(bucket, path.getUrl() + photoPath, tmp);
            return photoPath;

        } catch (AmazonServiceException e) {
            throw new BusinessException("Problem in Amazon Access" + " - " + e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            throw new BusinessException("The file does not exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public File getFile(Path path, String fileName) {
        try {
            if (!s3client.doesObjectExist(bucket, path.getUrl() + fileName)) {
                throw new BusinessException("The file does not exist", HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                File tmp = File.createTempFile("storeAppTmpRead", fileName);
                FileOutputStream fos = new FileOutputStream(tmp);
                S3Object o = s3client.getObject(bucket, path.getUrl() + fileName);
                S3ObjectInputStream s3is = o.getObjectContent();
                byte[] bytes = IOUtils.toByteArray(s3is);
                fos.write(bytes);
                s3is.close();
                fos.flush();
                fos.close();
                return tmp;
            }
        } catch (AmazonServiceException e) {
            throw new BusinessException("Problem in Amazon Access" + " - " + e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            throw new BusinessException("The file does not exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public String deleteFile(Path path, String fileName) {
        try {
            s3client.deleteObject(bucket, fileName);
            return fileName;
        } catch (AmazonServiceException e) {
            throw new BusinessException("Problem in Amazon Access" + " - " + e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            throw new BusinessException("The file does not exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
