package com.esgi.modules.post.application;

import com.esgi.kernel.Query;

public record IsPostLikedQuery(String postId, String userId) implements Query {
}
