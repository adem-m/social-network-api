package com.esgi.modules.follow.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.follow.domain.Follow;
import com.esgi.modules.follow.domain.FollowId;
import com.esgi.modules.follow.domain.FollowRepository;

public class CreateFollowCommandHandler implements CommandHandler<CreateFollow, FollowId> {
    private final FollowRepository followRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public CreateFollowCommandHandler(FollowRepository followRepository, EventDispatcher<Event> eventEventDispatcher){
        this.followRepository = followRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    @Override
    public FollowId handle(CreateFollow createFollow) {
        final FollowId followId = followRepository.nextIdentity();
        Follow follow;
        follow = new Follow(followId, createFollow.followerId, createFollow.followedId);
        followRepository.add(follow);
        eventEventDispatcher.dispatch(new CreateFollowEvent(followId));
        return followId;
    }
}
