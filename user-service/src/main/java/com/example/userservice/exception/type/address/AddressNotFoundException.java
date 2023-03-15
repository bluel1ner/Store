package com.example.userservice.exception.type.address;

/**
 * @author Neevels
 * @version 1.0
 * @date 3/15/2023 1:12 PM
 */
public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(String message) {
        super(message);
    }
}
