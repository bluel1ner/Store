package com.example.userservice.dto.request;

import com.example.userservice.entity.enums.Role;
import jakarta.persistence.Column;
import lombok.*;

/**
 * @author Neevels
 * @version 1.0
 * @date 5/4/2023 2:29 PM
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApplicationRequest {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String message;
    private Role role;
}
