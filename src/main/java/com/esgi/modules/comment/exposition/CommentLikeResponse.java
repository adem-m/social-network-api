package com.esgi.modules.comment.exposition;

public class CommentLikeResponse {
    public String id;
    public String userId;
    public String commentId;

    public CommentLikeResponse(String id, String userId, String commentId){
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
