package com.example.userservice.exception.type;

/**
 * @author Neevels
 * @version 1.0
 * @date 3/15/2023 12:28 PM
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
