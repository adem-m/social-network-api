package com.esgi.modules.comment.application;

import com.esgi.kernel.Command;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.user.domain.UserId;

import java.util.Objects;

/**
 * Command object
 */
@SuppressWarnings("all")
public final class CreateComment implements Command {
    public String postId;
    public String content;
    public final String creatorId;

    public CreateComment(String postId, String content, String creatorId) {
        this.postId = Objects.requireNonNull(postId);
        this.content = Objects.requireNonNull(content);
        this.creatorId = Objects.requireNonNull(creatorId);
    }
}