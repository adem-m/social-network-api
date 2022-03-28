package com.esgi.modules.friendship.exposition;

import com.esgi.modules.user.domain.UserId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FriendshipRequest {
    @NotNull
    @NotBlank
    public UserId userId1;

    @NotNull
    @NotBlank
    public UserId userId2;
}
