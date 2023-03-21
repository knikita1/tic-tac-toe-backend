package com.example.tictactoebackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidParameterException extends ResponseStatusException {
    private String message;

    public InvalidParameterException(HttpStatus status, String message) {
        super(status);
        this.message = message;
    }
}
