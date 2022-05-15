package com.esgi.modules.comment.exposition;

import javax.validation.constraints.NotNull;

public class CommentRequest {
    @NotNull
    public String postId;

    @NotNull
    public String content;
}
