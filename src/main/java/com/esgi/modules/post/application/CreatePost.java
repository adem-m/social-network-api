package com.esgi.modules.post.application;

import com.esgi.kernel.Command;
import com.esgi.modules.user.domain.UserId;

import java.util.Objects;

/**
 * Command object
 */
@SuppressWarnings("all")
public final class CreatePost implements Command {
    public String content;
    public final UserId creatorId;

    public CreatePost(String content, UserId creatorId) {
        this.content = Objects.requireNonNull(content);
        this.creatorId = Objects.requireNonNull(creatorId);
    }
}