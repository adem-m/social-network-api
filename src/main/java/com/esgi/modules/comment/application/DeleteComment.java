package com.esgi.modules.comment.application;

import com.esgi.kernel.Command;

public final class DeleteComment implements Command {
    public final String commentId;;

    public DeleteComment(String commentId) {
        this.commentId = commentId;
    }
}
