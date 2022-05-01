package com.esgi.modules.comment.application;

import com.esgi.kernel.Command;

public final class UnlikeComment implements Command {
    public final String userId;
    public final String commentId;;

    public UnlikeComment(String userId, String commentId) {
        this.userId = userId;
        this.commentId = commentId;
    }
}
