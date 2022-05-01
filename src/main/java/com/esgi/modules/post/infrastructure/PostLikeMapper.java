package com.esgi.modules.post.infrastructure;

import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.post.domain.PostLike;
import com.esgi.modules.post.domain.PostLikeId;
import com.esgi.modules.user.domain.UserId;
import org.springframework.stereotype.Component;

@Component
public class PostLikeMapper {
    public PostLikeEntity toEntity(PostLike postLike) {
        return new PostLikeEntity(postLike.id().getValue(), postLike.getUserId().getValue(), postLike.getPostId().getValue());
    }

    public PostLike toModel(PostLikeEntity postLikeEntity) {
        return new PostLike(
                new PostLikeId(postLikeEntity.getId()),
                new UserId(postLikeEntity.getUserId()),
                new PostId(postLikeEntity.getPostId()));
    }
}
