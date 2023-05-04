package com.example.userservice.dto.mapper;

import com.example.userservice.dto.request.CartRequest;
import com.example.userservice.dto.response.CartResponse;
import com.example.userservice.entity.mongo.Cart;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Neevels
 * @version 1.0
 * @date 5/2/2023 8:26 PM
 */
@Component
@NoArgsConstructor
public class CartMapper {
    public CartResponse toResponseDto(Cart cart) {
        return CartResponse.builder()
                .id(cart.getId())
                .color(cart.getColor())
                .configurations(cart.getConfigurations())
                .name(cart.getName())
                .price(cart.getPrice())
                .preview(cart.getPreview())
                .build();
    }

    public Cart toCart(CartRequest cartRequest, Long userId) {
        return Cart.builder()
                .id(cartRequest.getId())
                .color(cartRequest.getColor())
                .name(cartRequest.getName())
                .preview(cartRequest.getPreview())
                .price(cartRequest.getPrice())
                .userId(userId)
                .configurations(cartRequest.getConfigurations())
                .build();
    }
}
