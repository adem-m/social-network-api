package com.esgi.modules.post.exposition;

import javax.validation.constraints.NotNull;

public class PostLikeRequest {
    @NotNull
    public String postId;
}
