package com.esgi.modules.post.application;

import com.esgi.kernel.Command;

public final class EditPost implements Command {
    public final String postId;
    public String content;

    public EditPost(String postId, String content){
        this.postId = postId;
        this.content = content;
    }
}
