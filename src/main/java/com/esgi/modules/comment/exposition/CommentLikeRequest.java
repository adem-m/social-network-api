package com.esgi.modules.comment.exposition;

import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.user.domain.UserId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CommentLikeRequest {
    @NotNull
    public UserId userId;

    @NotNull
    public CommentId commentId;
}
