package com.esgi.modules.comment.infrastructure;

import com.esgi.kernel.NoSuchEntityException;
import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.comment.domain.CommentLike;
import com.esgi.modules.comment.domain.CommentLikeId;
import com.esgi.modules.comment.domain.CommentLikeRepository;
import com.esgi.modules.user.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class SpringDataCommentLikeRepository implements CommentLikeRepository {
    @Autowired
    private JpaCommentLikeRepository jpaCommentLikeRepository;
    @Autowired
    private CommentLikeMapper commentLikeMapper;

    @Override
    public CommentLikeId nextIdentity() {
        return new CommentLikeId(UUID.randomUUID().toString());
    }

    @Override
    public CommentLike findById(CommentLikeId id) throws NoSuchEntityException {
        return jpaCommentLikeRepository.findById(id.getValue())
                .map(commentLikeMapper::toModel)
                .orElseThrow(() -> NoSuchEntityException.withId(id));
    }

    @Override
    public void add(CommentLike entity) {
        jpaCommentLikeRepository.save(commentLikeMapper.toEntity(entity));
    }

    @Override
    public void delete(CommentLikeId id) {
        jpaCommentLikeRepository.deleteById(id.getValue());
    }

    @Override
    public List<CommentLike> findAll() {
        return jpaCommentLikeRepository.findAll().stream().map(commentLikeMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public List<CommentLike> findCommentsLikedByUserId(UserId userid) {
        return jpaCommentLikeRepository
                .findByUserId(userid.getValue())
                .stream().map(commentLikeMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public CommentLike findLikeByUserIdAndCommentId(UserId userId, CommentId commentId) {
        CommentLikeEntity commentLikeEntity = jpaCommentLikeRepository.findByUserIdAndCommentId(userId.getValue(), commentId.getValue());
        return commentLikeEntity == null ? null : commentLikeMapper.toModel(commentLikeEntity);
    }

    @Override
    public long countByCommentId(CommentId commentId) {
        return jpaCommentLikeRepository.countByCommentId(commentId.getValue());
    }

    @Override
    public boolean isLikedByUser(UserId userId, CommentId commentId) {
        return jpaCommentLikeRepository.existsByUserIdAndCommentId(userId.getValue(), commentId.getValue());
    }
}

@Repository
interface JpaCommentLikeRepository extends JpaRepository<CommentLikeEntity, String> {
    List<CommentLikeEntity> findByUserId(String id);

    CommentLikeEntity findByUserIdAndCommentId(String userId, String commentId);

    long countByCommentId(String commentId);

    boolean existsByUserIdAndCommentId(String userId, String commentId);
}

