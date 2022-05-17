package com.esgi.modules.comment.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.comment.domain.CommentLikeRepository;
import com.esgi.modules.user.domain.UserId;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record IsCommentLikedQueryHandler(
        CommentLikeRepository commentLikeRepository) implements QueryHandler<IsCommentLikedQuery, Boolean> {
    @Override
    public Boolean handle(IsCommentLikedQuery query) {
        log.info("Checking if comment {} is liked by user {}", query.commentId(), query.userId());
        return commentLikeRepository.isLikedByUser(new UserId(query.userId()), new CommentId(query.commentId()));
    }
}
