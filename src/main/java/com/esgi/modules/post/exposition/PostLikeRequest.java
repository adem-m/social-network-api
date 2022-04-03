package com.esgi.modules.post.exposition;

import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.user.domain.UserId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PostLikeRequest {
    @NotNull
    @NotBlank
    public UserId userId;

    @NotNull
    @NotBlank
    public PostId postId;
}
