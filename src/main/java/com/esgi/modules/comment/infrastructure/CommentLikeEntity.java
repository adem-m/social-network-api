package com.esgi.modules.comment.infrastructure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "commentLike")
public class CommentLikeEntity {
    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String commentId;

    public CommentLikeEntity(String id, String userId, String commentId) {
        this.id = id;
        this.userId = userId;
        this.commentId = commentId;
    }

    public CommentLikeEntity() {
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getCommentId() {
        return commentId;
    }
}
