package com.esgi.modules.follow.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.follow.domain.Follow;
import com.esgi.modules.follow.domain.FollowRepository;
import com.esgi.modules.user.domain.UserId;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class RetrieveFollowersHandler implements QueryHandler<RetrieveFollowers, List<Follow>> {
    private final FollowRepository followRepository;

    public RetrieveFollowersHandler(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    @Override
    public List<Follow> handle(RetrieveFollowers query) {
        UserId userId = new UserId(query.id);
        log.info("Retrieving followers for user {}", userId.getValue());
        return followRepository.findFollowersByUserId(userId);
    }
}
