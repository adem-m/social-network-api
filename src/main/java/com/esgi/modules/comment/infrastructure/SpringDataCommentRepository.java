package com.esgi.modules.comment.infrastructure;

import com.esgi.kernel.NoSuchEntityException;
import com.esgi.modules.comment.domain.Comment;
import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.comment.domain.CommentRepository;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.user.domain.UserId;

import java.util.List;

public class SpringDataCommentRepository implements CommentRepository {
    @Override
    public CommentId nextIdentity() {
        return null;
    }

    @Override
    public Comment findById(CommentId id) throws NoSuchEntityException {
        return null;
    }

    @Override
    public void add(Comment entity) {

    }

    @Override
    public void delete(CommentId id) {

    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public List<Comment> findByPostId(PostId postId) {
        return null;
    }

    @Override
    public List<Comment> findByUserId(UserId id) {
        return null;
    }
}
