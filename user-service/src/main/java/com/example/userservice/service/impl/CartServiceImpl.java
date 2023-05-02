package com.example.userservice.service.impl;

import com.example.userservice.dto.mapper.CartMapper;
import com.example.userservice.dto.request.CartRequest;
import com.example.userservice.dto.response.CartResponse;
import com.example.userservice.entity.User;
import com.example.userservice.entity.mongo.Cart;
import com.example.userservice.exception.type.BusinessException;
import com.example.userservice.repository.CartRepository;
import com.example.userservice.repository.ProductRepository;
import com.example.userservice.service.CartService;
import com.example.userservice.utils.UserUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 5/2/2023 7:48 PM
 */
@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final UserUtils userUtils;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, CartMapper cartMapper, UserUtils userUtils, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.userUtils = userUtils;
        this.productRepository = productRepository;
    }


    @Override
    public CartResponse addToCart(CartRequest cartRequest) {
        if (productRepository.findByName(cartRequest.getName()).isPresent()) {
            User user = userUtils.getUser();
            Cart cart = cartMapper.toCart(cartRequest, user.getId());
            return cartMapper.toResponseDto(cartRepository.save(cart));
        } else {
            throw new BusinessException(String.format("Product with name %s not found", cartRequest.getName()), HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public List<CartResponse> getAllUserCart() {
        User user = userUtils.getUser();
        return cartRepository.findAllByUserId(user.getId())
                .stream()
                .map(cartMapper::toResponseDto)
                .toList();
    }

    @Override
    public void deleteFromCart(String cartId) {
        cartRepository.findById(cartId)
                .ifPresentOrElse(cartRepository::delete, () -> {
                    throw new BusinessException("Product in cart doesnt found", HttpStatus.NOT_FOUND);
                });
    }

    @Override
    public CartResponse updateCart(CartRequest cartRequest) {
        User user = userUtils.getUser();
        cartRepository.findById(cartRequest.getId())
                .orElseThrow(() -> new BusinessException("Item at cart doesnt found", HttpStatus.NOT_FOUND));
        Cart cart = cartRepository.save(cartMapper.toCart(cartRequest, user.getId()));
        return cartMapper.toResponseDto(cart);
    }
}
