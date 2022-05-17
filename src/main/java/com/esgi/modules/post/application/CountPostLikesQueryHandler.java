package com.esgi.modules.post.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.post.domain.PostLikeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record CountPostLikesQueryHandler(
        PostLikeRepository repository) implements QueryHandler<CountPostLikesQuery, Long> {
    @Override
    public Long handle(CountPostLikesQuery query) {
        log.info("Counting likes for post {}", query.postId());
        return repository.countByPostId(new PostId(query.postId()));
    }
}
