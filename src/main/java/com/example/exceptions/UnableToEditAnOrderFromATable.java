package com.example.exceptions;


public class UnableToEditAnOrderFromATable extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnableToEditAnOrderFromATable(String message) {
        super(message);
    }
}
