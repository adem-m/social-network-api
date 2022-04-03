package com.esgi.modules.comment.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.comment.domain.CommentLike;
import com.esgi.modules.comment.domain.CommentLikeRepository;
import com.esgi.modules.post.application.RetrieveLikedPostsByUserId;
import com.esgi.modules.post.domain.PostLike;
import com.esgi.modules.post.domain.PostLikeRepository;

import java.util.List;

public class RetrieveLikedCommentsByUserIdHandler implements QueryHandler<RetrieveLikedCommentsByUserId, List<CommentLike>> {
    private final CommentLikeRepository commentLikeRepository;

    public RetrieveLikedCommentsByUserIdHandler(CommentLikeRepository commentLikeRepository) {
        this.commentLikeRepository = commentLikeRepository;
    }

    @Override
    public List<CommentLike> handle(RetrieveLikedCommentsByUserId query) {
        return commentLikeRepository.findCommentsLikedByUserId(query.id);
    }
}
