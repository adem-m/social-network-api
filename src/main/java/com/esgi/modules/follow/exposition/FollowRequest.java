package com.esgi.modules.follow.exposition;

import javax.validation.constraints.NotNull;

public class FollowRequest {
    @NotNull
    public String followerId;

    @NotNull
    public String followedId;
}
