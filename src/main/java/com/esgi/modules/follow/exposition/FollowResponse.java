package com.esgi.modules.follow.exposition;

import com.esgi.modules.user.domain.UserId;

public class FollowResponse {
    public String id;
    public UserId followerId;
    public UserId followedId;

    public FollowResponse(String id, UserId followerId, UserId followedId) {
        this.id = id;
        this.followerId = followerId;
        this.followedId = followedId;
    }

    @Override
    public String toString() {
        return "FollowDTO{" +
                "id=" + id +
                ", followerId='" + followerId + '\'' +
                ", followedId=" + followedId + '\'' +
                '}';
    }
}
