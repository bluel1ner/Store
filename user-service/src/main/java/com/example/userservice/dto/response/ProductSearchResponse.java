package com.example.userservice.dto.response;

import com.example.userservice.entity.enums.ProductType;
import lombok.Builder;

/**
 * @author Neevels
 * @version 1.0
 * @date 5/4/2023 1:57 PM
 */
@Builder
public record ProductSearchResponse (
        String id,
        String name,
        ProductType type
) {
}
