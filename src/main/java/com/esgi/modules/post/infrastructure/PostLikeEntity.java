package com.esgi.modules.post.infrastructure;

import javax.persistence.*;

@Entity
@Table(name = "postLike")
public class PostLikeEntity {
    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String postId;

    public PostLikeEntity() {
    }

    public PostLikeEntity(String id, String userId, String postId) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPostId() {
        return postId;
    }
}
