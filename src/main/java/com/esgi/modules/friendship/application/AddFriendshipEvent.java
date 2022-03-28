package com.esgi.modules.friendship.application;

import com.esgi.kernel.ApplicationEvent;
import com.esgi.modules.friendship.domain.FriendshipId;

public class AddFriendshipEvent implements ApplicationEvent {
    private final FriendshipId friendshipId;

    public AddFriendshipEvent(FriendshipId friendshipId){
        this.friendshipId = friendshipId;
    }
}
