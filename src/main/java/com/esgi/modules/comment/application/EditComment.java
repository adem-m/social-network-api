package com.esgi.modules.comment.application;

import com.esgi.kernel.Command;

public final class EditComment implements Command {
    public final int commentId;
    public String content;

    public EditComment(int commentId, String content){
        this.commentId = commentId;
        this.content = content;
    }
}
