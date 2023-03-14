package com.example.userservice.service.impl;

import com.example.userservice.dto.request.UserRequest;
import com.example.userservice.dto.response.UserResponse;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Neevels
 * @version 1.0
 * @date 2/13/2023 10:58 AM
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user ->
                        UserResponse.builder()
                                .firstName(user.getFirstName())
                                .lastName(user.getLastName())
                                .email(user.getEmail())
                                .role(user.getRole())
                                .phoneNumber(user.getPhoneNumber())
                                .build()

                )
                .toList();
    }


    @Override
    public UserResponse getUser() {
        User userById = getUserById();
        return UserResponse.builder()
                .firstName(userById.getFirstName())
                .lastName(userById.getLastName())
                .email(userById.getEmail())
                .phoneNumber(userById.getPhoneNumber())
                .role(userById.getRole())
                .build();
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest) {
        User userById = getUserById();
        userById.setFirstName(userRequest.getFirstName());
        userById.setLastName(userRequest.getLastName());
        userById.setPhoneNumber(userRequest.getPhoneNumber());
        User savedUser = userRepository.save(userById);
        return UserResponse.builder()
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .phoneNumber(savedUser.getPhoneNumber())
                .build();
    }


    private User getUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


}
