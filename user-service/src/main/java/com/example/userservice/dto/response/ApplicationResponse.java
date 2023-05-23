package com.example.userservice.dto.response;

import com.example.userservice.entity.enums.Role;
import lombok.Builder;

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
