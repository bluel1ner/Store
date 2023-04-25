package com.example.userservice.utils;

import com.example.userservice.entity.User;
import com.example.userservice.exception.type.user.UserNotFoundException;
import com.example.userservice.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/25/2023 1:15 PM
 */
@Component
public class UserUtils {

    private final UserRepository userRepository;

    public UserUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with email: %s not found", email)));
    }
}
