package com.esgi.modules.post.application;

import com.esgi.kernel.Command;

public class DeletePost implements Command {
    public final String postId;;

    public DeletePost(String postId) {
        this.postId = postId;
    }
}
