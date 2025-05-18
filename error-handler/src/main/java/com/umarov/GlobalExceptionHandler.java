package com.umarov;

import com.umarov.exceptions.SameEmailException;
import com.umarov.exceptions.UserNotFoundException;
import com.umarov.exceptions.UsernameAlreadyTaken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UsernameAlreadyTaken.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(UsernameAlreadyTaken ex) {
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(SameEmailException.class)
    public ResponseEntity<ErrorResponse> handleSameEmailException(SameEmailException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }


}