package com.esgi.modules.comment.exposition;

import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.user.domain.UserId;

public class CommentLikeResponse {
    public String id;
    public UserId userId;
    public CommentId commentId;

    public CommentLikeResponse(String id, UserId userId, CommentId commentId){
        this.id = id;
        this.userId = userId;
        this.commentId = commentId;
    }

    @Override
    public String toString() {
        return "CommentLikeDTO{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", commentId=" + commentId + '\'' +
                '}';
    }
}
