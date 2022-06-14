package com.esgi.modules.user.application;

import com.esgi.kernel.QueryBus;
import com.esgi.kernel.QueryHandler;
import com.esgi.modules.file.application.RetrieveImageQuery;
import com.esgi.modules.follow.domain.FollowRepository;
import com.esgi.modules.user.domain.User;
import com.esgi.modules.user.domain.UserId;
import com.esgi.modules.user.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class RetrieveUserByIdHandler implements QueryHandler<RetrieveUserById, User> {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final QueryBus queryBus;
    @Value("${image.thumbnail.suffix}")
    private String thumbnailSuffix;

    public RetrieveUserByIdHandler(FollowRepository followRepository, UserRepository userRepository, QueryBus queryBus) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
        this.queryBus = queryBus;
    }

    @Override
    public User handle(RetrieveUserById query) {
        UserId userId = new UserId(query.id);
        log.info("Retrieving user with id {}", userId.getValue());
        User user = userRepository.findById(userId);
        if (user.getImage() != null && !user.getImage().equals("")) {
            String imageName = query.thumbnail ? user.getImage() + thumbnailSuffix : user.getImage();
            String image = (String) queryBus.send(new RetrieveImageQuery(imageName));
            user.setImage(image);
        }
        if (query.userId != null) {
            boolean isFollowed = followRepository.isFollowing(new UserId(query.userId), userId);
            user.setFollowed(isFollowed);
        }
        return user;
    }
}
