package com.esgi.modules.comment.application;

import com.esgi.kernel.Command;

/**
 * Command object
 */
@SuppressWarnings("all")
public final class LikeComment implements Command {
    public final String userId;
    public final String commentId;

    public LikeComment(String userId, String commentId){
        this.userId = userId;
        this.commentId = commentId;
    }
}
