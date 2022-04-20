package com.esgi.modules.post.application;

import com.esgi.kernel.Command;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.user.domain.UserId;

public final class UnlikePost implements Command {
    public final String userId;
    public final String postId;


    public UnlikePost(String userId, String postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
