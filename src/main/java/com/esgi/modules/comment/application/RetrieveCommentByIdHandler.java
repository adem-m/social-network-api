package com.esgi.modules.comment.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.comment.domain.Comment;
import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.comment.domain.CommentRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RetrieveCommentByIdHandler implements QueryHandler<RetrieveCommentById, Comment> {
    private final CommentRepository commentRepository;

    public RetrieveCommentByIdHandler(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment handle(RetrieveCommentById query) {
        CommentId commentId = new CommentId(query.id);
        log.info("Retrieving comment with id {}", commentId.getValue());
        return commentRepository.findById(commentId);
    }
}
