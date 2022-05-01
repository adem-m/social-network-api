package com.esgi.modules.post.exposition;

import javax.validation.constraints.NotNull;

public class PostLikeRequest {
    @NotNull
    public String userId;

    @NotNull
    public String postId;
}
