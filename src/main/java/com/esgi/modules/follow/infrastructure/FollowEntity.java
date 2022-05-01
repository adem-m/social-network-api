package com.esgi.modules.follow.infrastructure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "follow")
public class FollowEntity {
    @Id
    @Column(nullable = false)
    private String followId;

    @Column(nullable = false)
    private String followerId;

    @Column(nullable = false)
    private String followedId;

    public FollowEntity() {
    }

    public FollowEntity(String followId, String followerId, String followedId) {
        this.followId = followId;
        this.followerId = followerId;
        this.followedId = followedId;
    }

    public String getFollowId() {
        return followId;
    }

    public String getFollowerId() {
        return followerId;
    }

    public String getFollowedId() {
        return followedId;
    }
}
