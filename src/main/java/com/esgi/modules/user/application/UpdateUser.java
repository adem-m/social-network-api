package com.esgi.modules.user.application;

import com.esgi.kernel.Command;
import com.esgi.modules.user.domain.Email;

public final class UpdateUser implements Command {
    public final String userId;
    public Email email;
    public String password;

    public UpdateUser(String userId, Email email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }
}
