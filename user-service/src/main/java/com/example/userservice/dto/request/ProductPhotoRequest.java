package com.example.userservice.dto.request;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/19/2023 11:55 AM
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductPhotoRequest {
    private String productId;
    private String productColor;
}
