package com.esgi.modules.post.application;

import com.esgi.kernel.Command;

public class DeletePost implements Command {
    public final String postId;
    public final String userId;

    public DeletePost(String postId, String userId) {
        this.postId = postId;
        this.userId = userId;
    }
}
