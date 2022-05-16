package com.esgi.modules.follow.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.follow.domain.Follow;
import com.esgi.modules.follow.domain.FollowRepository;
import com.esgi.modules.user.domain.UserId;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class RetrieveFollowingHandler implements QueryHandler<RetrieveFollowing, List<Follow>> {
    private final FollowRepository followRepository;

    public RetrieveFollowingHandler(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    @Override
    public List<Follow> handle(RetrieveFollowing query) {
        UserId userId = new UserId(query.id);
        log.info("Retrieving following for user {}", userId.getValue());
        return followRepository.findFollowingByUserId(userId);
    }
}

