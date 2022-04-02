package com.esgi.modules.comment.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.comment.domain.Comment;
import com.esgi.modules.comment.domain.CommentRepository;

public class RetrieveCommentByIdHandler implements QueryHandler<RetrieveCommentById, Comment> {
    private final CommentRepository commentRepository;

    public RetrieveCommentByIdHandler(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment handle(RetrieveCommentById query) {
        return commentRepository.findById(query.id);
    }
}
