package com.esgi.modules.follow.exposition;

import com.esgi.modules.user.domain.UserId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FollowRequest {
    @NotNull
    public int followerId;

    @NotNull
    public int followedId;
}
