package com.esgi.modules.infrastructure;

import com.esgi.kernel.NoSuchEntityException;
import com.esgi.modules.post.domain.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryPostLikeRepository implements PostLikeRepository {
    private final AtomicInteger count = new AtomicInteger(0);

    private final Map<PostLikeId, PostLike> data = new ConcurrentHashMap<>();

    @Override
    public PostLikeId nextIdentity() {
        return new PostLikeId(count.incrementAndGet());
    }

    @Override
    public PostLike findById(PostLikeId id) {
        final PostLike postLike = data.get(id);
        if (postLike == null) {
            throw NoSuchEntityException.withId(id);
        }
        return postLike;
    }

    @Override
    public void add(PostLike postLike) {
        data.put(postLike.getPostLikeId(), postLike);
    }

    @Override
    public void delete(PostLikeId id) {
        data.remove(id);
    }

    @Override
    public List<PostLike> findAll() {
        return List.copyOf(data.values());
    }
}
