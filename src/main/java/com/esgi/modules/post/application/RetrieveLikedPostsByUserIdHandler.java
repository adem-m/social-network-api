package com.esgi.modules.post.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.post.domain.PostLike;
import com.esgi.modules.post.domain.PostLikeRepository;

import java.util.List;

public class RetrieveLikedPostsByUserIdHandler implements QueryHandler<RetrieveLikedPostsByUserId, List<PostLike>> {

    private final PostLikeRepository postLikeRepository;

    public RetrieveLikedPostsByUserIdHandler(PostLikeRepository postLikeRepository) {
        this.postLikeRepository = postLikeRepository;
    }

    @Override
    public List<PostLike> handle(RetrieveLikedPostsByUserId query) {
        return postLikeRepository.findPostsLikedByUserId(query.id);
    }
}
