package com.esgi.modules.follow.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.follow.domain.Follow;
import com.esgi.modules.follow.domain.FollowRepository;
import com.esgi.modules.user.domain.UserId;

import java.util.List;

public class RetrieveFollowingHandler implements QueryHandler<RetrieveFollowing, List<Follow>> {
    private final FollowRepository followRepository;

    public RetrieveFollowingHandler(FollowRepository followRepository){
        this.followRepository = followRepository;
    }

    @Override
    public List<Follow> handle(RetrieveFollowing query) {
        UserId userId = new UserId(query.id);
        return followRepository.findFollowingByUserId(userId);
    }
}

