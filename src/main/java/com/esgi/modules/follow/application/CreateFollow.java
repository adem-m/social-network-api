package com.esgi.modules.follow.application;

import com.esgi.kernel.Command;

/**
 * Command object
 */
@SuppressWarnings("all")
public final class CreateFollow implements Command {
    public String followerId;
    public String followedId;

    public CreateFollow(String followerId, String followedId){
        this.followerId = followerId;
        this.followedId = followedId;
    }
}
