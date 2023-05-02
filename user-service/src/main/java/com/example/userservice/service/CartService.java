package com.example.userservice.service;

import com.example.userservice.dto.request.CartRequest;
import com.example.userservice.dto.response.CartResponse;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/26/2023 9:51 PM
 */
public interface CartService {

    CartResponse addToCart(CartRequest cartRequest);
    List<CartResponse> getAllUserCart();
    void deleteFromCart(String cartId);
    CartResponse updateCart(CartRequest cartRequest);

}
