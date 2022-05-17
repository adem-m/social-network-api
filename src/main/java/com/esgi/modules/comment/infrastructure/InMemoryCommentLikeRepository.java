package com.esgi.modules.comment.infrastructure;

import com.esgi.kernel.NoSuchEntityException;
import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.comment.domain.CommentLike;
import com.esgi.modules.comment.domain.CommentLikeId;
import com.esgi.modules.comment.domain.CommentLikeRepository;
import com.esgi.modules.user.domain.UserId;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public final class InMemoryCommentLikeRepository implements CommentLikeRepository {
    private final AtomicInteger count = new AtomicInteger(0);

    private final Map<CommentLikeId, CommentLike> data = new ConcurrentHashMap<>();

    @Override
    public CommentLikeId nextIdentity() {
        return new CommentLikeId(String.valueOf(count.incrementAndGet()));
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
        return List.copyOf(data.values().stream()
                .filter(commentLike -> commentLike.getUserId().equals(id)).collect(Collectors.toList()));
    }

    @Override
    public CommentLike findLikeByUserIdAndCommentId(UserId userId, CommentId commentId) {
        if (data.values().stream().noneMatch(commentLike -> commentLike.getUserId().equals(userId) && commentLike.getCommentId().equals(commentId))) {
            return null;
        }
        return data.values().stream()
                .filter(commentLike ->
                        commentLike.getUserId().equals(userId) && commentLike.getCommentId().equals(commentId))
                .toList().get(0);
    }

    @Override
    public long countByCommentId(CommentId commentId) {
        return data.values().stream().filter(commentLike -> commentLike.getCommentId().equals(commentId)).count();
    }

    @Override
    public boolean isLikedByUser(UserId userId, CommentId commentId) {
        return data.values().stream()
                .anyMatch(commentLike -> commentLike.getUserId().equals(userId) && commentLike.getCommentId().equals(commentId));
    }
}
