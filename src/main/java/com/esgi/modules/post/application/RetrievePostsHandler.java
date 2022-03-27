package com.esgi.modules.post.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.post.domain.Post;
import com.esgi.modules.post.domain.PostRepository;

import java.util.List;

public class RetrievePostsHandler implements QueryHandler<RetrievePosts, List<Post>> {

    private final PostRepository postRepository;

    public RetrievePostsHandler(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> handle(RetrievePosts query) {
        return postRepository.findAll();
    }
}
