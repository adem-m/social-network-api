package com.esgi.modules.comment.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.comment.domain.CommentLike;
import com.esgi.modules.comment.domain.CommentLikeRepository;
import com.esgi.modules.user.domain.UserId;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class RetrieveLikedCommentsByUserIdHandler implements QueryHandler<RetrieveLikedCommentsByUserId, List<CommentLike>> {
    private final CommentLikeRepository commentLikeRepository;

    public RetrieveLikedCommentsByUserIdHandler(CommentLikeRepository commentLikeRepository) {
        this.commentLikeRepository = commentLikeRepository;
    }

    @Override
    public List<CommentLike> handle(RetrieveLikedCommentsByUserId query) {
        UserId userId = new UserId(query.id);
        log.info("Retrieving liked comments by user id {}", userId.getValue());
        return commentLikeRepository.findCommentsLikedByUserId(userId);
    }
}
