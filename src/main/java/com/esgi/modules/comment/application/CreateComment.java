package com.esgi.modules.comment.application;

import com.esgi.kernel.Command;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.user.domain.UserId;

import java.util.Date;
import java.util.Objects;

/**
 * Command object
 */
@SuppressWarnings("all")
public final class CreateComment implements Command {
    public PostId postId;
    public String content;
    public final UserId creatorId;
    public final Date date;

    public CreateComment(PostId postId, String content, UserId creatorId, Date date) {
        this.postId = postId;
        this.content = Objects.requireNonNull(content);
        this.creatorId = Objects.requireNonNull(creatorId);
        this.date = Objects.requireNonNull(date);
    }
}