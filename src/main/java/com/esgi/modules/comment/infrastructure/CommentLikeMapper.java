package com.esgi.modules.comment.infrastructure;

import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.comment.domain.CommentLike;
import com.esgi.modules.comment.domain.CommentLikeId;
import com.esgi.modules.user.domain.UserId;
import org.springframework.stereotype.Component;

@Component
public class CommentLikeMapper {
    public CommentLikeEntity toEntity(CommentLike commentLike) {
        return new CommentLikeEntity(
                commentLike.getCommentLikeId().getValue(),
                commentLike.getUserId().getValue(),
                commentLike.getCommentId().getValue());
    }

    public CommentLike toModel(CommentLikeEntity commentLikeEntity) {
        return new CommentLike(
                new CommentLikeId(commentLikeEntity.getId()),
                new UserId(commentLikeEntity.getUserId()),
                new CommentId(commentLikeEntity.getCommentId()));
    }
}
