package com.esgi.modules.comment.domain;

import com.esgi.kernel.Repository;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.user.domain.UserId;

import java.util.List;

public interface CommentRepository extends Repository<CommentId, Comment> {
    List<Comment> findAll();
    List<Comment> findByPostId(PostId postId);
    List<Comment> findByUserId(UserId id);
}
