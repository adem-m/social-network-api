package com.esgi.modules.comment.domain;

import com.esgi.kernel.Repository;
import com.esgi.modules.user.domain.UserId;

import java.util.List;

public interface CommentRepository extends Repository<CommentId, Comment> {
    List<Comment> findAll();

    List<Comment> findCommentsByUserId(UserId id);
}
