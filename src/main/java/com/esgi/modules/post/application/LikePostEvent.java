package com.esgi.modules.post.application;

import com.esgi.kernel.ApplicationEvent;
import com.esgi.modules.post.domain.PostLikeId;

public class LikePostEvent implements ApplicationEvent {
    private final PostLikeId postLikeId;

    public LikePostEvent(PostLikeId postLikeId) {
        this.postLikeId = postLikeId;
    }
}
