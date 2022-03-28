package com.esgi.modules.friendship.domain;

import com.esgi.kernel.Entity;
import com.esgi.modules.user.domain.UserId;

public final class Friendship implements Entity<FriendshipId> {
    private final FriendshipId friendshipId;
    private final UserId userId1;
    private final UserId userId2;

    public Friendship(FriendshipId friendshipId, UserId userId1, UserId userId2){
        this.friendshipId = friendshipId;
        this.userId1 = userId1;
        this.userId2 = userId2;
    }

    @Override
    public FriendshipId id() {
        return friendshipId;
    }

    public FriendshipId getFriendshipId() {
        return friendshipId;
    }

    public UserId getUserId1() {
        return userId1;
    }

    public UserId getUserId2() {
        return userId2;
    }
}
