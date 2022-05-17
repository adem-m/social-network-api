package com.esgi.modules.comment.application;

import com.esgi.kernel.QueryBus;
import com.esgi.kernel.QueryHandler;
import com.esgi.modules.comment.domain.Comment;
import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.comment.domain.CommentRepository;
import com.esgi.modules.comment.domain.FullComment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RetrieveCommentByIdHandler implements QueryHandler<RetrieveCommentById, FullComment> {
    private final CommentRepository commentRepository;
    private final QueryBus queryBus;

    public RetrieveCommentByIdHandler(CommentRepository commentRepository, QueryBus queryBus) {
        this.commentRepository = commentRepository;
        this.queryBus = queryBus;
    }

    @Override
    public FullComment handle(RetrieveCommentById query) {
        CommentId commentId = new CommentId(query.id);
        log.info("Retrieving comment with id {}", commentId.getValue());
        Comment comment = commentRepository.findById(commentId);
        long likes = (Long) queryBus.send(new CountCommentLikesQuery(commentId.getValue()));
        return new FullComment(comment, likes);
    }
}
