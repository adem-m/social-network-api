package com.esgi.modules.comment.exposition;

import javax.validation.constraints.NotNull;

public class CommentLikeRequest {
    @NotNull
    public String commentId;
}
