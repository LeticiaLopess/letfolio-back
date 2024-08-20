package com.synchronia.letfolio.application.exception;

public class SynchroniaException extends Exception {

    private int statusCode;


    public SynchroniaException(String message) {
        super(message);
        this.statusCode = 500;
    }

    public SynchroniaException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    int getStatusCode() {
        return statusCode;
    }

}
