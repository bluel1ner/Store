package com.example.userservice.dto.response;

import com.example.userservice.entity.enums.Role;
import lombok.Builder;

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
