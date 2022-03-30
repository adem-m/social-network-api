package com.esgi.modules.follow.application;

import com.esgi.kernel.Command;
import com.esgi.modules.user.domain.UserId;

/**
 * Command object
 */
@SuppressWarnings("all")
public final class CreateFollow implements Command {
    public UserId followerId;
    public UserId followedId;

    public CreateFollow(UserId followerId, UserId followedId){
        this.followerId = followerId;
        this.followedId = followedId;
    }
}
