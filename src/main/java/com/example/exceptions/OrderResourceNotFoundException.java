package com.example.exceptions;

public class OrderResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OrderResourceNotFoundException(String message) {
        super(message);
    }
}
