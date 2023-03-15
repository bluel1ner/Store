package com.example.userservice.dto.request;

import jakarta.validation.constraints.Email;
import lombok.*;

/**
 * @author Neevels
 * @version 1.0
 * @date 2/7/2023 3:24 PM
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegisterRequest {

    private String firstName;
    private String lastName;
    @Email(message = "Email already exists")
    private String email;
    private String password;
}