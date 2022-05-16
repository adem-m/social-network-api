package com.esgi.kernel;

public class ForbiddenOperationException extends IllegalArgumentException {
    public ForbiddenOperationException(String message) {
        super(message);
    }
}
