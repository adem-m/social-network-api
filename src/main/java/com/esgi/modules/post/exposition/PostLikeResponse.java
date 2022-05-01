package com.esgi.modules.post.exposition;

public class PostLikeResponse {
    public String id;
    public String userId;
    public String postId;

    public PostLikeResponse(String id, String userId, String postId){
        this.id = id;
        this.userId = userId;
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "PostLikeDTO{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", postId=" + postId + '\'' +
                '}';
    }
}
