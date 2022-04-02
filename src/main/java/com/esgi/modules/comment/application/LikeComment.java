package com.esgi.modules.comment.application;

import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.user.domain.UserId;

public class LikeComment {
    public final UserId userId;
    public final CommentId commentId;

    public LikeComment(UserId userId, CommentId commentId){
        this.userId = userId;
        this.commentId = commentId;
    }
}
