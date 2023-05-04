package com.example.userservice.dto.response;

import com.example.userservice.entity.User;
import com.example.userservice.entity.enums.Role;
import lombok.Builder;

/**
 * @author Neevels
 * @version 1.0
 * @date 3/6/2023 8:50 PM
 */
@Builder
public record UserResponse(
        String email,
        String firstName,
        String lastName,
        String phoneNumber,
        Role role,
        String avatar
) {
}
