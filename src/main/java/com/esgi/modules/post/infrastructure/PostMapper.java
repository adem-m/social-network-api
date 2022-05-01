package com.esgi.modules.post.infrastructure;

import com.esgi.modules.post.domain.Post;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.user.domain.UserId;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public PostEntity toEntity(Post post) {
        return new PostEntity(post.getId().getValue(), post.getDate(), post.getContent(), post.getUserId().getValue());
    }

    public Post toModel(PostEntity postEntity) {
        return new Post(
                new PostId(postEntity.getId()),
                postEntity.getContent(),
                new UserId(postEntity.getCreatorId()),
                postEntity.getCreationDate());
    }
}
