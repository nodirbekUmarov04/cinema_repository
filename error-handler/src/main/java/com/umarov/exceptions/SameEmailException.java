package com.umarov.exceptions;

public class SameEmailException extends RuntimeException {
    public SameEmailException(String message) {
        super(message);
    }
}
