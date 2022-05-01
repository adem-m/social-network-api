package com.esgi.modules.comment.application;

import com.esgi.kernel.ApplicationEvent;
import com.esgi.modules.comment.domain.CommentLikeId;

public class LikeCommentEvent implements ApplicationEvent {
    private final CommentLikeId commentLikeId;

    public LikeCommentEvent(CommentLikeId commentLikeId) {
        this.commentLikeId = commentLikeId;
    }
}
