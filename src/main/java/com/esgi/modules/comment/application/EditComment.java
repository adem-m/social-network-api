package com.esgi.modules.comment.application;

import com.esgi.kernel.Command;

public final class EditComment implements Command {
    public final String commentId;
    public String content;

    public EditComment(String commentId, String content){
        this.commentId = commentId;
        this.content = content;
    }
}
