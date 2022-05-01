package com.esgi.kernel;

import com.esgi.modules.user.domain.Email;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }

    public static AlreadyExistsException withEmail(Email email){
        return new AlreadyExistsException(String.format("User with email %s already exist", email.getEmail()));
    }
}
