package com.example.live.exception;

public class CustomException extends RuntimeException{

    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }
}
