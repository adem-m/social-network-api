package com.esgi.modules.post.domain;

import com.esgi.kernel.Entity;
import com.esgi.modules.user.domain.UserId;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Post implements Entity<PostId> {
    private final PostId id;
    private String content;
    private final UserId userId;
    private LocalDateTime date;

    public Post(PostId id, String content, UserId userId, LocalDateTime creationDate) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.date = creationDate;
    }

    @Override
    public PostId id() {
        return id;
    }

    public PostId getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public UserId getUserId() {
        return userId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void changeContent(String newContent) {
        this.content = newContent;
    }

    public void changeLocalDateTime(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(content, post.content) && Objects.equals(userId, post.userId) && Objects.equals(date, post.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, userId, date);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", date=" + date +
                '}';
    }
}
