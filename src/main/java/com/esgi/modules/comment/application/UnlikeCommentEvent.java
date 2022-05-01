package com.esgi.modules.comment.application;

import com.esgi.kernel.ApplicationEvent;
import com.esgi.modules.comment.domain.CommentLikeId;

public class UnlikeCommentEvent implements ApplicationEvent {
    private final CommentLikeId commentLikeId;

    public UnlikeCommentEvent(CommentLikeId commentLikeId) {
        this.commentLikeId = commentLikeId;
    }
}
