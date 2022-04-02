package com.esgi.modules.comment.domain;

import com.esgi.kernel.Entity;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.user.domain.UserId;

import java.util.Date;
import java.util.Objects;

public final class Comment implements Entity<CommentId> {
    private final CommentId commentId;
    private final PostId postId;
    private String content;
    private final UserId userId;
    private final Date date;

    public Comment(CommentId commentId, PostId postId, String content, UserId userId) {
        this.commentId = commentId;
        this.postId = postId;
        this.content = content;
        this.userId = userId;
        this.date = new Date();
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

    public Date getDate() {
        return date;
    }

    public void changeContent(String newContent) {
        this.content = Objects.requireNonNull(newContent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(commentId, comment.commentId) && Objects.equals(postId, comment.postId) && Objects.equals(content, comment.content) && Objects.equals(userId, comment.userId) && Objects.equals(date, comment.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, postId, content, userId, date);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + commentId + '\'' +
                ", PostId=" + postId + '\'' +
                ", content='" + content + '\'' +
                ", creatorId='" + userId + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
