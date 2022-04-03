package com.esgi.modules.post.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.post.domain.Post;
import com.esgi.modules.post.domain.PostRepository;

import java.util.List;

public class RetrievePostsByUserIdHandler implements QueryHandler<RetrievePostsByUserId, List<Post>> {

    private final PostRepository postRepository;

    public RetrievePostsByUserIdHandler(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> handle(RetrievePostsByUserId query) {
        return postRepository.findPostsByUserId(query.id);
    }
}
