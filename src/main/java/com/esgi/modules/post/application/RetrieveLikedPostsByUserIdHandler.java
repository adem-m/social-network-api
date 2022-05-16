package com.esgi.modules.post.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.post.domain.PostLike;
import com.esgi.modules.post.domain.PostLikeRepository;
import com.esgi.modules.user.domain.UserId;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class RetrieveLikedPostsByUserIdHandler implements QueryHandler<RetrieveLikedPostsByUserId, List<PostLike>> {

    private final PostLikeRepository postLikeRepository;

    public RetrieveLikedPostsByUserIdHandler(PostLikeRepository postLikeRepository) {
        this.postLikeRepository = postLikeRepository;
    }

    @Override
    public List<PostLike> handle(RetrieveLikedPostsByUserId query) {
        UserId userId = new UserId(query.id);
        log.info("Retrieving liked posts by user id {}", userId.getValue());
        return postLikeRepository.findPostsLikedByUserId(userId);
    }
}
