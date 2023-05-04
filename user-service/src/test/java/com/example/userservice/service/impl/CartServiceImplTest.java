package com.example.userservice.service.impl;

import com.example.userservice.dto.response.CartResponse;
import com.example.userservice.entity.User;
import com.example.userservice.repository.ProductRepository;
import com.example.userservice.utils.UserUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Neevels
 * @version 1.0
 * @date 5/4/2023 3:23 PM
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private UserUtils userUtils;
    @InjectMocks
    private CartServiceImpl cartService;

    private static User user;

    private static List<CartResponse> cartResponseList;

    @BeforeAll
    static void setUp() {
        user = User.builder()
                .id(1l)
                .firstName("Ilya")
                .lastName("Ignatovich")
                .email("neevels@mail.ru")
                .build();

        cartResponseList = List.of(
                CartResponse.builder()
                        .id("123")
                        .price(123.3)
                        .name("Iphone")
                        .build(),
                CartResponse.builder()
                        .id("1235")
                        .price(123.3)
                        .name("Iphone12")
                        .build()
        );
    }

    @Test
    void addToCart() {


    }

    @Test
    void getAllUserCart() {
        when(userUtils.getUser()).thenReturn(user);
        when(cartService.getAllUserCart()).thenReturn(cartResponseList);
        List<CartResponse> allUserCart = cartService.getAllUserCart();
        assertEquals(allUserCart, cartResponseList);
    }

    @Test
    void deleteFromCart() {
    }

    @Test
    void updateCart() {
    }
}