package com.esgi.modules.follow.application;

import com.esgi.kernel.Command;

public final class Unfollow implements Command {
    public final String  unfollowerId;
    public final String unfollowedId;

    public Unfollow(String unfollowerId, String unfollowedId) {
        this.unfollowerId = unfollowerId;
        this.unfollowedId = unfollowedId;
    }
}
