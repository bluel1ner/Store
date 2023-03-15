package com.example.userservice.service.impl;

import com.example.userservice.config.JwtService;
import com.example.userservice.dto.request.AuthenticationRequest;
import com.example.userservice.dto.request.RegisterRequest;
import com.example.userservice.dto.response.AuthenticationResponse;
import com.example.userservice.entity.User;
import com.example.userservice.exception.type.UserAlreadyExistException;
import com.example.userservice.exception.type.UserNotFoundException;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * @author Neevels
 * @version 1.0
 * @date 2/7/2023 3:24 PM
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        if(repository.findByEmail(request.getEmail()).isEmpty()) {
            var user = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(User.Role.USER)
                    .build();
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } else {
            throw new UserAlreadyExistException(String.format("User with email %s already exist", request.getEmail()));
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() ->  new UserNotFoundException(String.format("User with email %s not found", request.getEmail())));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}