package com.esgi.modules.comment.application;

import com.esgi.kernel.ApplicationEvent;
import com.esgi.modules.comment.domain.CommentId;

public class CreateCommentEvent implements ApplicationEvent {
    private final CommentId commentId;

    public CreateCommentEvent(CommentId commentId) {
        this.commentId = commentId;
    }
}
