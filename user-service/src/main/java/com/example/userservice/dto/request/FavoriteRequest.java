package com.example.userservice.dto.request;

import lombok.*;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/26/2023 10:13 PM
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FavoriteRequest {
    private String productId;
}
