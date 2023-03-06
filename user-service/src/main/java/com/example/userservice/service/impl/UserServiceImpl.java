package com.example.userservice.service.impl;

import com.example.userservice.dto.response.UserResponse;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Neevels
 * @version 1.0
 * @date 2/13/2023 10:58 AM
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user ->
                        UserResponse.builder()
                                .firstName(user.getFirstName())
                                .lastName(user.getLastName())
                                .email(user.getEmail())
                                .role(user.getRole())
                                .phoneNumber(user.getPhoneNumber())
                                .build()

                )
                .toList();
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
        //FIXME: delete this method from service
    }

    @Override
    public UserResponse getUser() {
        Long userId = getUserId();
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            User userFromOptional = user.get();
            return UserResponse.builder()
                    .firstName(userFromOptional.getFirstName())
                    .lastName(userFromOptional.getLastName())
                    .email(userFromOptional.getEmail())
                    .phoneNumber(userFromOptional.getPhoneNumber())
                    .role(userFromOptional.getRole())
                    .build();

        } else {
            //TODO: throw exception if user not found(create exception "USER NOT FOUND")
            return null;
        }
    }


    private Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email).get().getId();
    }


}
