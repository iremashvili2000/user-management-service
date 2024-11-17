package com.example.usermanagmentservice.exception;

public class InvalidDataException extends RuntimeException{

    public InvalidDataException(String message) {
        super(message);
    }
}
