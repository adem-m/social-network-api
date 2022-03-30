package com.esgi.modules.follow.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.follow.domain.Follow;
import com.esgi.modules.follow.domain.FollowRepository;

import java.util.List;

public class RetrieveFollowsHandler implements QueryHandler<RetrieveFollows, List<Follow>> {
    private final FollowRepository followRepository;

    public RetrieveFollowsHandler(FollowRepository followRepository){
        this.followRepository = followRepository;
    }

    @Override
    public List<Follow> handle(RetrieveFollows query) {
        return followRepository.findByUserId(query.id);
    }
}

