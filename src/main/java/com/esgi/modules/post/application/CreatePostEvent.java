package com.esgi.modules.post.application;

import com.esgi.kernel.ApplicationEvent;
import com.esgi.modules.post.domain.PostId;

public class CreatePostEvent implements ApplicationEvent {
    private final PostId postId;

    public CreatePostEvent(PostId postId) {
        this.postId = postId;
    }
}
