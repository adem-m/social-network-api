package com.esgi.modules.user.application;

import com.esgi.kernel.Command;

public final class DeleteUser implements Command {
    public final String userId;;

    public DeleteUser(String userId) {
        this.userId = userId;
    }
}
