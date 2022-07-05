package com.esgi.modules.user.application;

public class PasswordDoesNotMatchException extends IllegalArgumentException {
    public PasswordDoesNotMatchException() {
        super("Password does not match");
    }
}
