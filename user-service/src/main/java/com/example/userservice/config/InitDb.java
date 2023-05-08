//package com.example.userservice.config;
//
//import com.example.userservice.entity.User;
//import com.example.userservice.entity.enums.Role;
//import com.example.userservice.repository.ProductRepository;
//import com.example.userservice.repository.UserRepository;
//import jakarta.annotation.PostConstruct;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class InitDb {
//    private Integer i = 0;
//    private final ProductRepository productRepository;
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//
//    public InitDb(ProductRepository productRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.productRepository = productRepository;
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @PostConstruct
//    public void initProductTable() {
//        for (int i = 0; i < 10; i++) {
////            addProduct();
////            addUser();
//        }
//    }
//
//    @PostConstruct
//    public void initUserTable() {
//        userRepository.save(
//                User.builder()
//                        .email("admin@gmail.com")
//                        .firstName("admin")
//                        .lastName("admin")
//                        .password(passwordEncoder.encode("111111"))
//                        .phoneNumber("11111111")
//                        .role(Role.ADMIN)
//                        .build()
//        );
//        userRepository.save(
//                User.builder()
//                        .email("manager@gmail.com")
//                        .firstName("admin")
//                        .role(Role.MANAGER)
//                        .lastName("admin")
//                        .password(passwordEncoder.encode("111111"))
//                        .phoneNumber("11111111")
//                        .build()
//        );
//    }
//
//
//    private void addUser() {
//        userRepository.save(
//                User.builder()
//                        .email("jkl" + ++i)
//                        .firstName("ghjkl")
//                        .lastName("rtyuio")
//                        .password("567890")
//                        .phoneNumber("23567890")
//                        .build()
//        );
//    }
//
//}
