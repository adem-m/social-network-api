package com.esgi.modules.post.application;

import com.esgi.kernel.Command;

public final class UnlikePost implements Command {
    public final int postId;;

    public UnlikePost(int postId) {
        this.postId = postId;
    }
}
