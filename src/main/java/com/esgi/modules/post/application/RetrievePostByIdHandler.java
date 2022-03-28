package com.esgi.modules.post.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.post.domain.Post;
import com.esgi.modules.post.domain.PostRepository;

public class RetrievePostByIdHandler implements QueryHandler<RetrievePostById, Post> {
    private final PostRepository postRepository;

    public RetrievePostByIdHandler(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post handle(RetrievePostById query) {
        return postRepository.findById(query.id);
    }
}
