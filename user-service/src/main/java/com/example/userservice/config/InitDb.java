package com.example.userservice.config;

import com.example.userservice.entity.*;
import com.example.userservice.entity.enums.ProductType;
import com.example.userservice.repository.ProductRepository;
import com.example.userservice.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;


@Component
public class InitDb {
    private Integer i = 0;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    public InitDb(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initProductTable() {
        for (int i = 0; i < 10; i++) {
//            addProduct();
//            addUser();
        }
    }

    @PostConstruct
    public void initUserTable() {

    }


    private void addUser() {
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
