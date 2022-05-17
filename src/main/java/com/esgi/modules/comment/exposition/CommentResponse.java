package com.esgi.modules.comment.exposition;

import com.esgi.kernel.CoreUserResponse;

public class CommentResponse {
    public String commentId;
    public String postId;
    public String content;
    public CoreUserResponse creator;
    public String date;
    public long likes;
    public boolean isLiked;

    public CommentResponse(String commentId, String postId, String content, CoreUserResponse creator, String date, long likes, boolean isLiked) {
        this.commentId = commentId;
        this.postId = postId;
        this.content = content;
        this.creator = creator;
        this.date = date;
        this.likes = likes;
        this.isLiked = isLiked;
    }

    @Override
    public String toString() {
        return "CommentResponse{" +
                "commentId='" + commentId + '\'' +
                ", postId='" + postId + '\'' +
                ", content='" + content + '\'' +
                ", creator=" + creator +
                ", date='" + date + '\'' +
                ", likes=" + likes +
                ", isLiked=" + isLiked +
                '}';
    }
}
