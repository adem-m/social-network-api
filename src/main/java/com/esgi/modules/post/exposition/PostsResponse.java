package com.esgi.modules.post.exposition;

import java.util.List;

@SuppressWarnings("all")
public class PostsResponse {
    public final List<PostResponse> posts;

    public PostsResponse(List<PostResponse> posts) {
        this.posts = posts;
    }
}
