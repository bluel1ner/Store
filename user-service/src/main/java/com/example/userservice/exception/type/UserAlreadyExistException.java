package com.example.userservice.exception.type;

/**
 * @author Neevels
 * @version 1.0
 * @date 3/15/2023 12:38 PM
 */
public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
