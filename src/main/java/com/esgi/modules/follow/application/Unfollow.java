package com.esgi.modules.follow.application;

import com.esgi.kernel.Command;

public final class Unfollow implements Command {
    public final int userId;;

    public Unfollow(int userId) {
        this.userId = userId;
    }
}
