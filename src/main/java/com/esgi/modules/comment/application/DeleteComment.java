package com.esgi.modules.comment.application;

import com.esgi.kernel.Command;

public final class DeleteComment implements Command {
    public final int commentId;;

    public DeleteComment(int commentId) {
        this.commentId = commentId;
    }
}
