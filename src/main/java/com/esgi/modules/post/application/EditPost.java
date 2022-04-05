package com.esgi.modules.post.application;

import com.esgi.kernel.Command;

import java.util.Date;

public final class EditPost implements Command {
    public final int postId;
    public String content;

    public EditPost(int postId, String content){
        this.postId = postId;
        this.content = content;
    }
}
