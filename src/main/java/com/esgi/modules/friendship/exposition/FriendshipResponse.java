package com.esgi.modules.friendship.exposition;

import com.esgi.modules.user.domain.UserId;

public class FriendshipResponse {
    public String id;
    public UserId userId1;
    public UserId userId2;

    public FriendshipResponse(String id,  UserId userId1, UserId userId2) {
        this.id = id;
        this.userId1 = userId1;
        this.userId2 = userId2;
    }

    @Override
    public String toString() {
        return "FriendshipDTO{" +
                "id=" + id +
                ", userId1='" + userId1 + '\'' +
                ", userId2=" + userId2 + '\'' +
                '}';
    }
}
