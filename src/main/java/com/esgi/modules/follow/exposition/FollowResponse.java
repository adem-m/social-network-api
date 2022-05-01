package com.esgi.modules.follow.exposition;

public class FollowResponse {
    public String id;
    public String followerId;
    public String followedId;

    public FollowResponse(String id, String followerId, String followedId) {
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
