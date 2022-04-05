package com.esgi.modules.comment.application;

import com.esgi.kernel.Command;

public final class UnlikeComment implements Command {
    public final int commentId;;

    public UnlikeComment(int commentId) {
        this.commentId = commentId;
    }
}
