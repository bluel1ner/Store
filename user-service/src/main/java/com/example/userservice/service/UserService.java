package com.example.userservice.service;

import com.example.userservice.dto.request.ChangePasswordRequest;
import com.example.userservice.dto.request.UserRequest;
import com.example.userservice.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();

    UserResponse getUser();

    UserResponse updateUser(UserRequest userRequest);

    String changePassword(ChangePasswordRequest password);
}
