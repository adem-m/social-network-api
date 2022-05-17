package com.esgi.modules.post.application;

import com.esgi.kernel.QueryBus;
import com.esgi.kernel.QueryHandler;
import com.esgi.modules.post.domain.FullPost;
import com.esgi.modules.post.domain.Post;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.post.domain.PostRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RetrievePostByIdHandler implements QueryHandler<RetrievePostById, FullPost> {
    private final PostRepository postRepository;
    private final QueryBus queryBus;

    public RetrievePostByIdHandler(PostRepository postRepository, QueryBus queryBus) {
        this.postRepository = postRepository;
        this.queryBus = queryBus;
    }

    @Override
    public FullPost handle(RetrievePostById query) {
        PostId postId = new PostId(query.id);
        log.info("Retrieving post with id {}", postId.getValue());
        Long likes = (Long) queryBus.send(new CountPostLikesQuery(postId.getValue()));
        Post post = postRepository.findById(postId);
        return new FullPost(post, likes);
    }
}
