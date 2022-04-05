package com.esgi.modules.follow.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.follow.domain.Follow;
import com.esgi.modules.follow.domain.FollowRepository;
import com.esgi.modules.user.domain.UserId;

import java.util.List;

public class RetrieveFollowersHandler implements QueryHandler<RetrieveFollowers, List<Follow>> {
    private final FollowRepository followRepository;

    public RetrieveFollowersHandler(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    @Override
    public List<Follow> handle(RetrieveFollowers query) {
        UserId userId = new UserId(query.id);
        return followRepository.findFollowersByUserId(userId);
    }
}
