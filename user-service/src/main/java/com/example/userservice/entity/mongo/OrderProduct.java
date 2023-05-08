package com.example.userservice.entity.mongo;

import com.example.userservice.dto.request.CartRequest;
import com.example.userservice.entity.enums.ProductType;
import lombok.*;

/**
 * @author Neevels
 * @version 1.0
 * @date 5/2/2023 7:40 PM
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderProduct {
    private CartRequest product;
    private Integer amount;
    private ProductType type;
}
