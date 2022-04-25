package com.esgi.modules.post.infrastructure;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "post")
public class PostEntity {
    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    private String content;

    @Column(nullable = false)
    private String creatorId;

    public PostEntity() {
    }

    public PostEntity(String id, LocalDateTime creationDate, String content, String creatorId) {
        this.id = id;
        this.creationDate = creationDate;
        this.content = content;
        this.creatorId = creatorId;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getContent() {
        return content;
    }

    public String getCreatorId() {
        return creatorId;
    }
}
