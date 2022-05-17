package com.esgi.modules.post.application;

import com.esgi.kernel.Query;

public record CountPostLikesQuery(String postId) implements Query {
}
