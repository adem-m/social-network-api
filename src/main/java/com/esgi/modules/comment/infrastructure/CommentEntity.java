package com.esgi.modules.comment.infrastructure;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    private String id;
}
