package com.example.userservice.dto.response;

import com.example.userservice.entity.enums.ProductType;
import lombok.Builder;

@Builder
public record FavoriteResponse (
        Integer id,
        String productId,
        String productName,
        String productPreview,
        ProductType productType
) {
}
