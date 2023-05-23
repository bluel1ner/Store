package com.example.userservice.entity.mongo;

import com.example.userservice.dto.request.CartRequest;
import com.example.userservice.entity.enums.ProductType;
import lombok.*;

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
