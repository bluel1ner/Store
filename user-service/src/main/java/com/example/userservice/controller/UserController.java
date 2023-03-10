package com.example.userservice.controller;

import com.example.userservice.dto.response.UserResponse;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 2/7/2023 12:49 PM
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    private ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/getUser")
    private ResponseEntity<UserResponse> getUser() {
        return ResponseEntity.ok(userService.getUser());
    }


}
