package com.esgi.modules.post.application;

import com.esgi.kernel.QueryBus;
import com.esgi.kernel.QueryHandler;
import com.esgi.modules.post.domain.FullPost;
import com.esgi.modules.post.domain.Post;
import com.esgi.modules.post.domain.PostRepository;
import com.esgi.modules.user.domain.UserId;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class RetrieveFeedByUserIdHandler implements QueryHandler<RetrieveFeedByUserId, List<FullPost>> {
    private final PostRepository postRepository;
    private final QueryBus queryBus;

    public RetrieveFeedByUserIdHandler(PostRepository postRepository, QueryBus queryBus) {
        this.postRepository = postRepository;
        this.queryBus = queryBus;
    }

    @Override
    public List<FullPost> handle(RetrieveFeedByUserId query) {
        UserId userId = new UserId(query.id);
        log.info("Retrieving feed for user {}", userId.getValue());
        List<Post> posts = postRepository.findFeedByUserId(userId);
        return posts.stream().map(post -> {
            Long likes = (Long) queryBus.send(new CountPostLikesQuery(post.id().getValue()));
            return new FullPost(post, likes);
        }).toList();
    }
}
