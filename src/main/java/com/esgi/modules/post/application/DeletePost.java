package com.esgi.modules.post.application;

import com.esgi.kernel.Command;

public class DeletePost implements Command {
    public final int postId;;

    public DeletePost(int postId) {
        this.postId = postId;
    }
}
