package org.example.exceptions;

public class DataNotExistsException extends RuntimeException {

    private String message;

    public DataNotExistsException(String message) {
        super(message);
        this.message = message;
    }
}
