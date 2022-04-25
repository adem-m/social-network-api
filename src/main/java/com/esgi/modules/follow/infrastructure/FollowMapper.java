package com.esgi.modules.follow.infrastructure;

import com.esgi.modules.follow.domain.Follow;
import com.esgi.modules.follow.domain.FollowId;
import com.esgi.modules.post.domain.Post;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.post.infrastructure.PostEntity;
import com.esgi.modules.user.domain.UserId;
import org.springframework.stereotype.Component;

@Component
public class FollowMapper {
    public FollowEntity toEntity(Follow follow) {
        return new FollowEntity(follow.getFollowId().getValue(), follow.getFollowerId().getValue(), follow.getFollowedId().getValue());
    }

    public Follow toModel(FollowEntity followEntity) {
        return new Follow(
                new FollowId(followEntity.getFollowId()),
                new UserId(followEntity.getFollowerId()),
                new UserId(followEntity.getFollowedId()));
    }
}
