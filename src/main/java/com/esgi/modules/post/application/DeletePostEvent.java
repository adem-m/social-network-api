package com.esgi.modules.post.application;

import com.esgi.kernel.ApplicationEvent;
import com.esgi.modules.post.domain.PostId;

public class DeletePostEvent implements ApplicationEvent {
    private final PostId postId;

    public DeletePostEvent(PostId postId) {
        this.postId = postId;
    }
}
