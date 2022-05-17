package com.esgi.modules.comment.application;

import com.esgi.kernel.QueryBus;
import com.esgi.kernel.QueryHandler;
import com.esgi.modules.comment.domain.Comment;
import com.esgi.modules.comment.domain.CommentRepository;
import com.esgi.modules.comment.domain.FullComment;
import com.esgi.modules.user.domain.UserId;

import java.util.List;

public class RetrieveCommentsHandler implements QueryHandler<RetrieveComments, List<FullComment>> {
    private final CommentRepository commentRepository;
    private final QueryBus queryBus;

    public RetrieveCommentsHandler(CommentRepository commentRepository, QueryBus queryBus) {
        this.commentRepository = commentRepository;
        this.queryBus = queryBus;
    }

    @Override
    public List<FullComment> handle(RetrieveComments query) {
        UserId userId = new UserId(query.id);
        List<Comment> comments = commentRepository.findByUserId(userId);
        return comments.stream().map(
                comment -> {
                    long likes = (Long) queryBus.send(new CountCommentLikesQuery(comment.getCommentId().getValue()));
                    return new FullComment(comment, likes);
                }
        ).toList();
    }
}
