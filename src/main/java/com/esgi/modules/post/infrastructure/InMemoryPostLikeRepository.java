package com.esgi.modules.post.infrastructure;

import com.esgi.kernel.NoSuchEntityException;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.post.domain.PostLike;
import com.esgi.modules.post.domain.PostLikeId;
import com.esgi.modules.post.domain.PostLikeRepository;
import com.esgi.modules.user.domain.UserId;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public final class InMemoryPostLikeRepository implements PostLikeRepository {
    private final AtomicInteger count = new AtomicInteger(0);

    private final Map<PostLikeId, PostLike> data = new ConcurrentHashMap<>();

    @Override
    public PostLikeId nextIdentity() {
        return new PostLikeId(String.valueOf(count.incrementAndGet()));
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

    @Override
    public List<PostLike> findPostsLikedByUserId(UserId id) {
        return List.copyOf(data.values().stream()
                .filter(postLike -> postLike.getUserId().equals(id)).collect(Collectors.toList()));
    }

    @Override
    public PostLike findLikeByUserIdAndPostId(UserId userId, PostId postId) {
        if (data.values().stream().noneMatch(postLike -> postLike.getUserId().equals(userId) && postLike.getPostId().equals(postId))) {
            return null;
        }
        return data.values().stream()
                .filter(postLike -> postLike.getUserId().equals(userId) && postLike.getPostId().equals(postId)).collect(Collectors.toList()).get(0);
    }

    @Override
    public long countByPostId(PostId postId) {
        return data.values().stream().filter(postLike -> postLike.getPostId().equals(postId)).count();
    }

    @Override
    public boolean isLikedByUser(UserId userId, PostId postId) {
        return data.values().stream()
                .anyMatch(postLike -> postLike.getUserId().equals(userId) && postLike.getPostId().equals(postId));
    }
}
