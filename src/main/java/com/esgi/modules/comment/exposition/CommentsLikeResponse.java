package com.esgi.modules.comment.exposition;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@SuppressWarnings("all")
@XmlRootElement
public class CommentsLikeResponse {
    public final List<CommentLikeResponse> commentsLiked;

    public CommentsLikeResponse(List<CommentLikeResponse> commentsLiked) {
            this.commentsLiked = commentsLiked;
        }
}
