package com.example.exceptions;

import java.io.Serial;

public class CannotDeleteABusyTable extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CannotDeleteABusyTable(String message) {
        super(message);
    }
}

