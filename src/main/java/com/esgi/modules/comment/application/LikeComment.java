package com.esgi.modules.comment.application;

import com.esgi.kernel.Command;
import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.user.domain.UserId;

/**
 * Command object
 */
@SuppressWarnings("all")
public final class LikeComment implements Command {
    public final UserId userId;
    public final CommentId commentId;

    public LikeComment(UserId userId, CommentId commentId){
        this.userId = userId;
        this.commentId = commentId;
    }
}
