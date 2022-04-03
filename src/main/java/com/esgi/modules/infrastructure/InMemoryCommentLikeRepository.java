package com.esgi.modules.infrastructure;

import com.esgi.kernel.NoSuchEntityException;
import com.esgi.modules.comment.domain.CommentLike;
import com.esgi.modules.comment.domain.CommentLikeId;
import com.esgi.modules.comment.domain.CommentLikeRepository;
import com.esgi.modules.user.domain.UserId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class InMemoryCommentLikeRepository implements CommentLikeRepository {
    private final AtomicInteger count = new AtomicInteger(0);

    private final Map<CommentLikeId, CommentLike> data = new ConcurrentHashMap<>();

    @Override
    public CommentLikeId nextIdentity() {
        return new CommentLikeId(count.incrementAndGet());
    }

    @Override
    public CommentLike findById(CommentLikeId id) {
        final CommentLike commentLike = data.get(id);
        if (commentLike == null) {
            throw NoSuchEntityException.withId(id);
        }
        return commentLike;
    }

    @Override
    public void add(CommentLike commentLike) {
        data.put(commentLike.getCommentLikeId(), commentLike);
    }

    @Override
    public void delete(CommentLikeId id) {
        data.remove(id);
    }

    @Override
    public List<CommentLike> findAll() {
        return List.copyOf(data.values());
    }

    @Override
    public List<CommentLike> findCommentsLikedByUserId(UserId id) {
        List<CommentLike> allCommentLike = List.copyOf(data.values());
        List<CommentLike> result = new ArrayList<>();
        for (CommentLike commentLike : allCommentLike) {
            if (commentLike.getUserId() == id)
                result.add(commentLike);
        }
        return result;
    }
}
