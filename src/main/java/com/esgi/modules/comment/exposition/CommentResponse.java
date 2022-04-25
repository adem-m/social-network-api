package com.esgi.modules.comment.exposition;

import java.time.LocalDateTime;

public class CommentResponse {
    public String commentId;
    public String postId;
    public String content;
    public String userId;
    public LocalDateTime date;

    public CommentResponse(String commentId, String postId, String content, String userId, LocalDateTime date){
        this.commentId = commentId;
        this.postId = postId;
        this.content = content;
        this.userId = userId;
        this.date = date;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "commentId=" + commentId +
                ", postId=" + postId + '\'' +
                ", content='" + content + '\'' +
                ", creatorId='" + userId + '\'' +
                ", date=" + date + '\'' +
                '}';
    }
}
