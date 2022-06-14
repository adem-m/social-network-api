package com.esgi.modules.user.application;

public class InvalidImageException extends IllegalArgumentException {
    public InvalidImageException(String message) {
        super(message);
    }
}
