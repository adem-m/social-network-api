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
    public PostId postId;
    public String content;
    public final UserId creatorId;

    public CreateComment(PostId postId, String content, UserId creatorId) {
        this.postId = postId;
        this.content = Objects.requireNonNull(content);
        this.creatorId = Objects.requireNonNull(creatorId);
    }
}