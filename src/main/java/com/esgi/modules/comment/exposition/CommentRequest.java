package com.esgi.modules.comment.exposition;

import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.user.domain.UserId;

import javax.validation.constraints.NotNull;

public class CommentRequest {
    @NotNull
    public PostId postId;

    @NotNull
    public String content;

    @NotNull
    public UserId userId;
}
