package com.example.userservice.dto.response;

import com.example.userservice.entity.enums.Role;
import jakarta.persistence.Column;
import lombok.Builder;

/**
 * @author Neevels
 * @version 1.0
 * @date 5/4/2023 2:25 PM
 */
@Builder
public record ApplicationResponse(
         Integer id,
         String firstName,
         String lastName,
         String phone,
         String email,
         String message,
         Role role
){
}
