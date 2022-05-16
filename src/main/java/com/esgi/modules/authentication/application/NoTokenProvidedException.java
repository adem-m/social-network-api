package com.esgi.modules.authentication.application;

final public class NoTokenProvidedException extends RuntimeException {
    public NoTokenProvidedException() {
        super("No token provided");
    }
}
