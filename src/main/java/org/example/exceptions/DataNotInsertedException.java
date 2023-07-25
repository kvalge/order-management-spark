package org.example.exceptions;

public class DataNotInsertedException extends RuntimeException {

    private String message;

    public DataNotInsertedException(String message) {
        super(message);
        this.message = message;
    }
}
