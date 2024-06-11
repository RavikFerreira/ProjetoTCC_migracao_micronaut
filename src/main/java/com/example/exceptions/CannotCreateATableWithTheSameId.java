package com.example.exceptions;

import java.io.Serial;

public class CannotCreateATableWithTheSameId extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CannotCreateATableWithTheSameId(String message) {
        super(message);
    }
}
