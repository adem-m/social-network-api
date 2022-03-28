package com.esgi.modules.post.domain;

import com.esgi.kernel.Entity;
import com.esgi.modules.user.domain.UserId;

import java.util.Objects;

public final class PostLike implements Entity<PostLikeId> {
    private final PostLikeId postLikeId;
    private final UserId userId;
    private final PostId postId;

    public PostLike(PostLikeId postLikeId,UserId userId, PostId postId){
        this.postLikeId = postLikeId;
        this.userId = userId;
        this.postId = postId;
    }

    @Override
    public PostLikeId id() {
        return postLikeId;
    }

    public PostLikeId getPostLikeId() {
        return postLikeId;
    }

    public UserId getUserId() {
        return userId;
    }

    public PostId getPostId() {
        return postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostLike postLike = (PostLike) o;
        return Objects.equals(userId, postLike.userId) && Objects.equals(postId, postLike.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, postId);
    }

    @Override
    public String toString() {
        return "PostLike{" +
                ", userId='" + userId + '\'' +
                ", postId='" + postId + '\'' +
                '}';
    }
}
