package com.esgi.modules.comment.exposition;

import java.util.Date;

public class CommentResponse {
    public String commentId;
    public String postId;
    public String content;
    public String userId;
    public Date date;

    public CommentResponse(String commentId, String postId, String content, String userId, Date date){
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
