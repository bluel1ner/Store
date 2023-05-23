package com.example.userservice.controller;

import com.example.userservice.dto.request.ProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/add")
    public void addSmth(@RequestParam("file") MultipartFile multipartFile1,
                        @RequestPart("request") ProductRequest productRequest) {
        log.info(productRequest.toString());
        log.info(multipartFile1.toString());
    }
}
