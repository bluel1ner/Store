package com.example.userservice.dto.request;

import lombok.*;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/20/2023 11:16 PM
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeletePhotoRequest {
    private String productId;
    private String photoPath;
}
