package com.example.userservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/8/2023 5:57 PM
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
}
