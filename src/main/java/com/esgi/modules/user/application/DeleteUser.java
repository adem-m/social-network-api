package com.esgi.modules.user.application;

import com.esgi.kernel.Command;

public final class DeleteUser implements Command {
    public final int userId;;

    public DeleteUser(int userId) {
        this.userId = userId;
    }
}
