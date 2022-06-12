package com.esgi.modules.user.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.follow.domain.FollowRepository;
import com.esgi.modules.user.domain.User;
import com.esgi.modules.user.domain.UserId;
import com.esgi.modules.user.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RetrieveUserByIdHandler implements QueryHandler<RetrieveUserById, User> {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public RetrieveUserByIdHandler(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User handle(RetrieveUserById query) {
        UserId userId = new UserId(query.id);
        log.info("Retrieving user with id {}", userId.getValue());
        User user = userRepository.findById(userId);
        if (query.userId != null) {
            boolean isFollowed = followRepository.isFollowing(new UserId(query.userId), userId);
            user.setFollowed(isFollowed);
        }
        return user;
    }
}
