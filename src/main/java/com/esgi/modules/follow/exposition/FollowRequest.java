package com.esgi.modules.follow.exposition;

import com.esgi.modules.user.domain.UserId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FollowRequest {
    @NotNull
    @NotBlank
    public UserId followerId;

    @NotNull
    @NotBlank
    public UserId followedId;
}
