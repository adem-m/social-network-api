package com.esgi.modules.comment.infrastructure;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String postId;

    private String content;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private LocalDateTime date;

    public CommentEntity(String id, String postId, String content, String userId, LocalDateTime date) {
        this.id = id;
        this.postId = postId;
        this.content = content;
        this.userId = userId;
        this.date = date;
    }

    public CommentEntity() {
    }

    public String getId() {
        return id;
    }

    public String getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
