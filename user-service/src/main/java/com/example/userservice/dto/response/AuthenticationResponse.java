package com.example.userservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Neevels
 * @version 1.0
 * @date 2/7/2023 3:24 PM
 */
@Builder
public record AuthenticationResponse(
        String token,
        UserResponse userResponse
) {
}