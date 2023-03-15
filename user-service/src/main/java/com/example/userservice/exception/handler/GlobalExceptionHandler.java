package com.example.userservice.exception.handler;

import com.example.userservice.exception.model.Exception;
import com.example.userservice.exception.type.address.AddressNotFoundException;
import com.example.userservice.exception.type.user.UserAlreadyExistException;
import com.example.userservice.exception.type.user.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author Neevels
 * @version 1.0
 * @date 3/15/2023 11:35 AM
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
        HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;
        Exception ex = Exception.builder()
                .message(e.getMessage())
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .httpStatus(notFoundStatus)
                .build();
        return new ResponseEntity<>(ex, notFoundStatus);
    }

    @ExceptionHandler(value = UserAlreadyExistException.class)
    protected ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException e) {
        HttpStatus conflictStatus = HttpStatus.CONFLICT;
        Exception ex = Exception.builder()
                .message(e.getMessage())
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .httpStatus(conflictStatus)
                .build();
        return new ResponseEntity<>(ex, conflictStatus);
    }

    @ExceptionHandler(value = AddressNotFoundException.class)
    protected ResponseEntity<Object> handleUserAlreadyExistException(AddressNotFoundException e) {
        HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;
        Exception ex = Exception.builder()
                .message(e.getMessage())
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .httpStatus(notFoundStatus)
                .build();
        return new ResponseEntity<>(ex, notFoundStatus);
    }


}
