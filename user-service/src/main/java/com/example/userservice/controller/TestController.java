package com.example.userservice.controller;

import com.example.userservice.entity.User;
import com.example.userservice.repository.AddressRepository;
import com.example.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController()
@RequestMapping("/test")
public class TestController {
    Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public User getInfo() {
        User byId = userRepository.findById(1);
        logger.info(String.valueOf(byId));
        return null;
    }
}
