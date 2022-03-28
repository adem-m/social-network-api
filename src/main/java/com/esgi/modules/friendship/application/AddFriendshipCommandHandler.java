package com.esgi.modules.friendship.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.friendship.domain.Friendship;
import com.esgi.modules.friendship.domain.FriendshipId;
import com.esgi.modules.friendship.domain.FriendshipRepository;

public class AddFriendshipCommandHandler implements CommandHandler<AddFriendship, FriendshipId> {
    private final FriendshipRepository friendshipRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public AddFriendshipCommandHandler(FriendshipRepository friendshipRepository, EventDispatcher<Event> eventEventDispatcher){
        this.friendshipRepository = friendshipRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    @Override
    public FriendshipId handle(AddFriendship addFriendship) {
        final FriendshipId friendshipId = friendshipRepository.nextIdentity();
        Friendship friendship;
        friendship = new Friendship(friendshipId, addFriendship.userId1, addFriendship.userId2);
        friendshipRepository.add(friendship);
        eventEventDispatcher.dispatch(new AddFriendshipEvent(friendshipId));
        return friendshipId;
    }
}
