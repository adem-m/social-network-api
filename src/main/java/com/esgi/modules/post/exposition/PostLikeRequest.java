package com.esgi.modules.post.exposition;

import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.user.domain.UserId;

import javax.validation.constraints.NotNull;

public class PostLikeRequest {
    @NotNull
    public UserId userId;

    @NotNull
    public PostId postId;
}
