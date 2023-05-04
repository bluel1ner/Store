package com.example.userservice.dto.response;

import com.example.userservice.entity.enums.ProductType;
import lombok.Builder;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/26/2023 10:13 PM
 */
@Builder
public record FavoriteResponse (
        Integer id,
        String productId,
        String productName,
        String productPreview,
        ProductType productType
) {
}
