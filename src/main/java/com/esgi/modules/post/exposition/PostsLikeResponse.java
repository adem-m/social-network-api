package com.esgi.modules.post.exposition;

import java.util.List;

@SuppressWarnings("all")
public class PostsLikeResponse {
    public final List<PostLikeResponse> postsLiked;

    public PostsLikeResponse(List<PostLikeResponse> postsLiked) {
        this.postsLiked = postsLiked;
    }
}
