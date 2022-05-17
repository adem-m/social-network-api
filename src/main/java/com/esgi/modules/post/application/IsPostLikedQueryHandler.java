package com.esgi.modules.post.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.post.domain.PostLikeRepository;
import com.esgi.modules.user.domain.UserId;

public record IsPostLikedQueryHandler(
        PostLikeRepository postLikeRepository) implements QueryHandler<IsPostLikedQuery, Boolean> {
    @Override
    public Boolean handle(IsPostLikedQuery query) {
        return postLikeRepository.isLikedByUser(new UserId(query.userId()), new PostId(query.postId()));
    }
}
