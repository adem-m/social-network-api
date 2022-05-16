package com.esgi.modules.authentication.application;

public class WrongPasswordException extends IllegalStateException {
    public WrongPasswordException() {
        super("Wrong email/password combination");
    }
}
