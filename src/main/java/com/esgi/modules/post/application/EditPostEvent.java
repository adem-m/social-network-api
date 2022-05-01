package com.esgi.modules.post.application;

import com.esgi.kernel.ApplicationEvent;
import com.esgi.modules.post.domain.PostId;

public class EditPostEvent implements ApplicationEvent {
    private final PostId postId;

    public EditPostEvent(PostId postId) {
        this.postId = postId;
    }
}
