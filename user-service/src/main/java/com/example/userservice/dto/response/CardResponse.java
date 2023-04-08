package com.example.userservice.dto.response;

import com.example.userservice.entity.enums.Status;
import lombok.Builder;

@Builder
public record CardResponse(
        Integer id,
        String number,
        String validityDate,
        String owner,
        Status status
) {
}
