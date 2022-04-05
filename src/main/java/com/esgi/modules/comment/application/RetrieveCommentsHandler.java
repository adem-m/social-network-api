package com.esgi.modules.comment.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.comment.domain.Comment;
import com.esgi.modules.comment.domain.CommentRepository;
import com.esgi.modules.user.domain.UserId;

import java.util.List;

public class RetrieveCommentsHandler implements QueryHandler<RetrieveComments, List<Comment>> {

    private final CommentRepository commentRepository;

    public RetrieveCommentsHandler(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> handle(RetrieveComments query) {
        UserId userId = new UserId(query.id);
        return commentRepository.findByUserId(userId);
    }
}
