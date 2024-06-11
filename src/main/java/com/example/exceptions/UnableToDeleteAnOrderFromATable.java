package com.example.exceptions;

public class UnableToDeleteAnOrderFromATable extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnableToDeleteAnOrderFromATable(String message) {
        super(message);
    }
}

