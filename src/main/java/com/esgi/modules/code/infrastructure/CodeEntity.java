package com.esgi.modules.code.infrastructure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "code")
public class CodeEntity {
    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String postId;

    @Column(nullable = false)
    private String source;

    @Column(nullable = false)
    private String language;

    public CodeEntity(String id, String postId, String source, String language) {
        this.id = id;
        this.postId = postId;
        this.source = source;
        this.language = language;
    }

    public CodeEntity() {
    }

    public String getId() {
        return id;
    }

    public String getPostId() {
        return postId;
    }

    public String getSource() {
        return source;
    }

    public String getLanguage() {
        return language;
    }
}
