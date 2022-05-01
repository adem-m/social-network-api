package com.esgi.modules.comment.domain;

import com.esgi.kernel.Repository;
import com.esgi.modules.user.domain.UserId;

import java.util.List;

public interface CommentLikeRepository extends Repository<CommentLikeId, CommentLike> {
    List<CommentLike> findAll();
    List<CommentLike> findCommentsLikedByUserId(UserId id);
    CommentLike findLikeByUserIdAndCommentId(UserId userId, CommentId commentId);
}
