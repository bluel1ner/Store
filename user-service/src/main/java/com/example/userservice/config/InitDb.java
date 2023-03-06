package com.example.userservice.config;

import com.example.userservice.entity.*;
import com.example.userservice.entity.enums.ProductType;
import com.example.userservice.repository.ProductRepository;
import com.example.userservice.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;


@Component
public class InitDb {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public InitDb(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initProductTable() {
        for (int i = 0; i < 100; i++) {
            productRepository.save(
                    Product.builder()
                            .brand("s")
                            .color("Blue")
                            .name("Iphone")
                            .productType(ProductType.IPhone)
                            .price(500.9)
                            .amount(5)
                            .averageRate(7.8f)
                            .description("qqq")
                            .originCountry("USA")
                            .build()
            );
        }
    }

    @PostConstruct
    public void initUserTable() {
        for (int i = 0; i < 100; i++) {
            userRepository.save(
                    User.builder()
                            .email("jkl" + ++i)
                            .firstName("ghjkl")
                            .lastName("rtyuio")
                            .password("567890")
                            .phoneNumber("23567890")
                            .build()
            );
        }
    }
}
