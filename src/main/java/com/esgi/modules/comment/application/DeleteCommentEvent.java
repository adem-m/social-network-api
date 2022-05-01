package com.esgi.modules.comment.application;

import com.esgi.kernel.ApplicationEvent;
import com.esgi.modules.comment.domain.CommentId;

public class DeleteCommentEvent implements ApplicationEvent {
    private final CommentId commentId;

    public DeleteCommentEvent(CommentId commentId) {
        this.commentId = commentId;
    }
}
