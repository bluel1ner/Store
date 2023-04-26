package com.example.userservice.dto.response;

import lombok.Builder;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/26/2023 10:13 PM
 */
@Builder
public record FavoriteResponse (
        String productName,
        String preview,
        Integer price,
        Integer favoriteId
) {
}
