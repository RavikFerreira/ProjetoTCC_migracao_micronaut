package com.example.exceptions;

public class TablesResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TablesResourceNotFoundException(String message) {
        super(message);
    }
}
