package com.example.userservice.config;

import com.example.userservice.entity.User;
import com.example.userservice.entity.enums.USER_ROLE;
import com.example.userservice.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class InitDb {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initUserTable() {
        if (!(userRepository.findAll().size() > 0)) {
            userRepository.save(
                    User.builder()
                            .email("admin@gmail.com")
                            .firstName("admin")
                            .lastName("admin")
                            .password(passwordEncoder.encode("111111"))
                            .phoneNumber("11111111")
                            .role(USER_ROLE.ADMIN)
                            .build()
            );
            userRepository.save(
                    User.builder()
                            .email("manager@gmail.com")
                            .firstName("admin")
                            .role(USER_ROLE.MANAGER)
                            .lastName("admin")
                            .password(passwordEncoder.encode("111111"))
                            .phoneNumber("11111111")
                            .build()
            );
        }
    }

}
