package com.umarov.exceptions;

public class UsernameAlreadyTaken extends RuntimeException {
    public UsernameAlreadyTaken(String message) {
        super(message);
    }
}
