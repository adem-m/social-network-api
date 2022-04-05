package com.esgi.modules.post.application;

import com.esgi.kernel.ApplicationEvent;
import com.esgi.modules.post.domain.PostLikeId;

public class UnlikePostEvent implements ApplicationEvent {
    private final PostLikeId postLikeId;

    public UnlikePostEvent(PostLikeId postLikeId) {
        this.postLikeId = postLikeId;
    }
}
