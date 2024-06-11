package com.example.exceptions;

import java.io.Serial;

public class ItIsNotAllowedToAddOrdersWithTheSameId extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ItIsNotAllowedToAddOrdersWithTheSameId (String message) {
        super(message);
    }
}
