package com.esgi.modules.post.application;

import com.esgi.kernel.Command;
import com.esgi.modules.code.exposition.CodeRequest;

public final class EditPost implements Command {
    public final String postId;
    public String content;
    public CodeRequest code;
    public String userId;

    public EditPost(String postId, String content, CodeRequest code, String userId) {
        this.postId = postId;
        this.content = content;
        this.code = code;
        this.userId = userId;
    }
}
