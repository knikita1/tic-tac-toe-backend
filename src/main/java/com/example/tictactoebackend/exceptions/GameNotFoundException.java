package com.example.tictactoebackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GameNotFoundException extends ResponseStatusException {
    private String message;

    public GameNotFoundException(HttpStatus status, String message) {
        super(status);
        this.message = message;
    }
}
