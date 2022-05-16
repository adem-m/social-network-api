package com.esgi.modules.comment.exposition;

import java.util.List;

@SuppressWarnings("all")
public class CommentsLikeResponse {
    public final List<CommentLikeResponse> commentsLiked;

    public CommentsLikeResponse(List<CommentLikeResponse> commentsLiked) {
        this.commentsLiked = commentsLiked;
    }
}
