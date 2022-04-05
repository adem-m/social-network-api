package com.esgi.modules.comment.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.comment.domain.CommentLike;
import com.esgi.modules.comment.domain.CommentLikeRepository;
import com.esgi.modules.user.domain.UserId;

import java.util.List;

public class RetrieveLikedCommentsByUserIdHandler implements QueryHandler<RetrieveLikedCommentsByUserId, List<CommentLike>> {
    private final CommentLikeRepository commentLikeRepository;

    public RetrieveLikedCommentsByUserIdHandler(CommentLikeRepository commentLikeRepository) {
        this.commentLikeRepository = commentLikeRepository;
    }

    @Override
    public List<CommentLike> handle(RetrieveLikedCommentsByUserId query) {
        UserId userId = new UserId(query.id);
        return commentLikeRepository.findCommentsLikedByUserId(userId);
    }
}
