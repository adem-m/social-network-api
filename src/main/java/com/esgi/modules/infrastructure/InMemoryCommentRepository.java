package com.esgi.modules.infrastructure;

import com.esgi.kernel.NoSuchEntityException;
import com.esgi.modules.comment.domain.Comment;
import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.comment.domain.CommentRepository;
import com.esgi.modules.user.domain.UserId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class InMemoryCommentRepository implements CommentRepository {
    private final AtomicInteger count = new AtomicInteger(0);

    private final Map<CommentId, Comment> data = new ConcurrentHashMap<>();

    @Override
    public CommentId nextIdentity() {
        return new CommentId(count.incrementAndGet());
    }

    @Override
    public Comment findById(CommentId id) {
        final Comment comment = data.get(id);
        if (comment == null) {
            throw NoSuchEntityException.withId(id);
        }
        return comment;
    }

    @Override
    public void add(Comment comment) {
        data.put(comment.getCommentId(), comment);
    }

    @Override
    public void delete(CommentId id) {
        data.remove(id);
    }

    @Override
    public List<Comment> findAll() {
        return List.copyOf(data.values());
    }

    @Override
    public List<Comment> findCommentsByUserId(UserId id) {
        List<Comment> allComment = List.copyOf(data.values());
        List<Comment> result = new ArrayList<>();
        for (Comment comment : allComment) {
            if (comment.getUserId() == id)
                result.add(comment);
        }
        return result;
    }
}
