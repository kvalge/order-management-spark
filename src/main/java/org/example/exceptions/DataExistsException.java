package org.example.exceptions;

public class DataExistsException extends RuntimeException {

    private String message;

    public DataExistsException(String message) {
        super(message);
        this.message = message;
    }
}
