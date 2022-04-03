package com.esgi.modules.post.exposition;

import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.user.domain.UserId;

import java.util.Date;

public class PostLikeResponse {
    public String id;
    public UserId userId;
    public PostId postId;

    public PostLikeResponse(String id, UserId userId, PostId postId){
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
