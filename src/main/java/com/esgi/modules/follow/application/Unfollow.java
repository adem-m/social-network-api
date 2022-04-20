package com.esgi.modules.follow.application;

import com.esgi.kernel.Command;
import com.esgi.modules.user.domain.UserId;

public final class Unfollow implements Command {
    public final String  unfollowerId;
    public final String unfollowedId;

    public Unfollow(String unfollowerId, String unfollowedId) {
        this.unfollowerId = unfollowerId;
        this.unfollowedId = unfollowedId;
    }
}
