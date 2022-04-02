package com.esgi.modules.comment.domain;

import com.esgi.kernel.Entity;
import com.esgi.modules.user.domain.UserId;

import java.util.Objects;

public final class CommentLike implements Entity<CommentLikeId> {
    private final CommentLikeId commentLikeId;
    private final UserId userId;
    private final CommentId commentId;

    public CommentLike(CommentLikeId commentLikeId, UserId userId, CommentId commentId){
        this.commentLikeId = commentLikeId;
        this.userId = userId;
        this.commentId = commentId;
    }

    @Override
    public CommentLikeId id() {
        return commentLikeId;
    }

    public CommentLikeId getPostLikeId() {
        return commentLikeId;
    }

    public UserId getUserId() {
        return userId;
    }

    public CommentId getPostId() {
        return commentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentLike commentLike = (CommentLike) o;
        return Objects.equals(userId, commentLike.userId) && Objects.equals(commentId, commentLike.commentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, commentId);
    }

    @Override
    public String toString() {
        return "CommentLike{" +
                ", userId='" + userId + '\'' +
                ", postId='" + commentId + '\'' +
                '}';
    }
}
