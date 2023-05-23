package com.example.userservice.dto.request;

import com.example.userservice.entity.enums.Role;
import lombok.*;

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
