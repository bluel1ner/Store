package com.example.userservice.dto.mapper;

import com.example.userservice.dto.response.UserResponse;
import com.example.userservice.entity.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/9/2023 11:55 AM
 */
@NoArgsConstructor
@Component
public class UserMapper {
    public UserResponse toResponseDto(User user) {
        return UserResponse.builder()
                .avatar(user.getAvatar())
                .role(user.getRole())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
