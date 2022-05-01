package com.esgi.modules.comment.application;

import com.esgi.kernel.ApplicationEvent;
import com.esgi.modules.comment.domain.CommentId;

public class EditCommentEvent implements ApplicationEvent {
    private final CommentId commentId;

    public EditCommentEvent(CommentId commentId) {
        this.commentId = commentId;
    }
}
