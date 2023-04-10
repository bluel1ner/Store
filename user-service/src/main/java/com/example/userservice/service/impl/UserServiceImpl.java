package com.example.userservice.service.impl;

import com.example.userservice.dto.request.ChangePasswordRequest;
import com.example.userservice.dto.request.UserRequest;
import com.example.userservice.dto.response.UserResponse;
import com.example.userservice.entity.User;
import com.example.userservice.exception.type.BusinessException;
import com.example.userservice.exception.type.user.UserNotFoundException;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 2/13/2023 10:58 AM
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

    @Override
    public String changePassword(ChangePasswordRequest changePasswordRequest) {
        User user = getUserById();
        String oldPassword = user.getPassword();

        if (passwordEncoder.matches(changePasswordRequest.getNewPassword(),oldPassword) ||
                changePasswordRequest.getNewPassword().equals(changePasswordRequest.getOldPassword())
        ) {
            throw new BusinessException("Old and new password are the same. Please, try again!", HttpStatus.CONFLICT);
        }
        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(),oldPassword)) {
            throw new BusinessException("Old password doesn't correct. Please, try again!", HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        userRepository.save(user);
        return "Password successfully changed";
    }


    private User getUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with email: %s not found", email)));
    }


}
