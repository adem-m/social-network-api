package com.esgi.modules.post.domain;

import com.esgi.modules.user.domain.UserId;

import java.util.Objects;

public final class PostLike {
    private final UserId userId;
    private final PostId postId;

    public PostLike(UserId userId, PostId postId){
        this.userId = userId;
        this.postId = postId;
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
