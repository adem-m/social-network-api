package com.esgi.modules.post.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.post.domain.Post;
import com.esgi.modules.post.domain.PostRepository;
import com.esgi.modules.user.domain.UserId;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class RetrievePostsByUserIdHandler implements QueryHandler<RetrievePostsByUserId, List<Post>> {

    private final PostRepository postRepository;

    public RetrievePostsByUserIdHandler(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> handle(RetrievePostsByUserId query) {
        UserId userId = new UserId(query.id);
        log.info("Retrieving posts by user id {}", userId.getValue());
        return postRepository.findPostsByUserId(userId);
    }
}
