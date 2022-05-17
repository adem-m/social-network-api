package com.esgi.modules.comment.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.comment.domain.CommentLikeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record CountCommentLikesQueryHandler(
        CommentLikeRepository commentLikeRepository) implements QueryHandler<CountCommentLikesQuery, Long> {
    @Override
    public Long handle(CountCommentLikesQuery query) {
        log.info("Counting likes for comment {}", query.commentId());
        return commentLikeRepository.countByCommentId(new CommentId(query.commentId()));
    }
}
