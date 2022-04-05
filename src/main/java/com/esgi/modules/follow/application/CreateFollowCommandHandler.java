package com.esgi.modules.follow.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.follow.domain.Follow;
import com.esgi.modules.follow.domain.FollowId;
import com.esgi.modules.follow.domain.FollowRepository;
import com.esgi.modules.user.application.RetrieveUserById;

import javax.persistence.EntityExistsException;

public final class CreateFollowCommandHandler implements CommandHandler<CreateFollow, FollowId> {
    private final FollowRepository followRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public CreateFollowCommandHandler(FollowRepository followRepository, EventDispatcher<Event> eventEventDispatcher){
        this.followRepository = followRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    @Override
    public FollowId handle(CreateFollow createFollow) {
        if(followRepository.findFollowBetweenTwoUser(createFollow.followerId, createFollow.followedId) == null) {
            final FollowId followId = followRepository.nextIdentity();
            Follow follow = new Follow(followId, createFollow.followerId, createFollow.followedId);
            followRepository.add(follow);
            eventEventDispatcher.dispatch(new CreateFollowEvent(followId));
            return followId;
        }
        throw new EntityExistsException(createFollow.followerId + " is already following " + createFollow.followedId);
    }
}
