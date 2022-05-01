package com.esgi.modules.comment.domain;

import com.esgi.kernel.Entity;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.user.domain.UserId;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Comment implements Entity<CommentId> {
    private final CommentId commentId;
    private final PostId postId;
    private String content;
    private final UserId userId;
    private final LocalDateTime creationDate;

    public Comment(CommentId commentId, PostId postId, String content, UserId userId, LocalDateTime creationDate) {
        this.commentId = commentId;
        this.postId = postId;
        this.content = content;
        this.userId = userId;
        this.creationDate = creationDate;
    }

    @Override
    public CommentId id() {
        return commentId;
    }

    public CommentId getCommentId() {
        return commentId;
    }

    public PostId getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }

    public UserId getUserId() {
        return userId;
    }

    public LocalDateTime getDate() {
        return creationDate;
    }

    public void changeContent(String newContent) {
        this.content = Objects.requireNonNull(newContent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(commentId, comment.commentId) &&
                Objects.equals(postId, comment.postId) &&
                Objects.equals(content, comment.content) &&
                Objects.equals(userId, comment.userId) &&
                Objects.equals(creationDate, comment.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, postId, content, userId, creationDate);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", postId=" + postId +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", date=" + creationDate +
                '}';
    }
}
