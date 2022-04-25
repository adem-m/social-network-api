package com.esgi.modules.follow.domain;

import com.esgi.kernel.Entity;
import com.esgi.modules.user.domain.UserId;

public final class Follow implements Entity<FollowId>{
    private final FollowId followId;
    private final UserId followerId;
    private final UserId followedId;

    public Follow(FollowId followId, UserId followerId, UserId followedId){
        this.followId = followId;
        this.followerId = followerId;
        this.followedId = followedId;
    }

    @Override
    public FollowId id() {
        return followId;
    }

    public FollowId getFollowId() {
        return followId;
    }

    public UserId getFollowerId() {
        return followerId;
    }

    public UserId getFollowedId() {
        return followedId;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "followId=" + followId +
                ", followerId=" + followerId +
                ", followedId=" + followedId +
                '}';
    }
}