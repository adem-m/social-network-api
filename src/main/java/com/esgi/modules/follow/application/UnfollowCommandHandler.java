package com.esgi.modules.follow.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.follow.domain.FollowId;
import com.esgi.modules.follow.domain.FollowRepository;
import com.esgi.modules.user.domain.UserId;

public final class UnfollowCommandHandler implements CommandHandler<Unfollow, FollowId> {
    private final FollowRepository followRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public UnfollowCommandHandler(FollowRepository followRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.followRepository = followRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    public FollowId handle(Unfollow unfollow) {
        final UserId unfollowerId = new UserId(Integer.parseInt(unfollow.unfollowerId));
        final UserId unfollowedId = new UserId(Integer.parseInt(unfollow.unfollowedId));
        final FollowId followId = followRepository.findFollowBetweenTwoUser(unfollowerId, unfollowedId).getFollowId();
        followRepository.delete(followId);
        eventEventDispatcher.dispatch(new UnfollowEvent(followId));
        return followId;
    }
}
