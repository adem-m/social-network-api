package com.esgi.modules.user.application;

import com.esgi.kernel.Command;
import com.esgi.modules.user.domain.Email;

import java.util.Objects;

public final class UpdateUser implements Command {
    public final int userId;
    public Email email;

    public UpdateUser(int userId, Email email) {
        this.userId = userId;
        this.email = Objects.requireNonNull(email);
    }
}
