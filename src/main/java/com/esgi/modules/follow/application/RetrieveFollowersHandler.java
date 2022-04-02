package com.esgi.modules.follow.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.follow.domain.Follow;
import com.esgi.modules.follow.domain.FollowRepository;

import java.util.List;

public class RetrieveFollowersHandler implements QueryHandler<RetrieveFollowing, List<Follow>> {
    private final FollowRepository followRepository;

    public RetrieveFollowersHandler(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    @Override
    public List<Follow> handle(RetrieveFollowing query) {
        return followRepository.findFollowersByUserId(query.id);
    }
}
