package com.esgi.modules.infrastructure;

import com.esgi.kernel.NoSuchEntityException;
import com.esgi.modules.post.domain.Post;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.post.domain.PostRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryPostRepository implements PostRepository {
    private final AtomicInteger count = new AtomicInteger(0);

    private final Map<PostId, Post> data = new ConcurrentHashMap<>();

    @Override
    public PostId nextIdentity() {
        return new PostId(count.incrementAndGet());
    }

    @Override
    public Post findById(PostId id) {
        final Post post = data.get(id);
        if (post == null) {
            throw NoSuchEntityException.withId(id);
        }
        return post;
    }

    @Override
    public void add(Post post) {
        data.put(post.getId(), post);
    }

    @Override
    public void delete(PostId id) {
        data.remove(id);
    }

    @Override
    public List<Post> findAll() {
        return List.copyOf(data.values());
    }
}

