package com.example.userservice.dto.response;

import com.example.userservice.entity.mongo.Configuration;
import lombok.Builder;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 5/2/2023 8:23 PM
 */
@Builder
public record CartResponse(
        String id,
        String name,
        String preview,
        Double price,
        String color,
        List<Configuration> configurations
) {
}
