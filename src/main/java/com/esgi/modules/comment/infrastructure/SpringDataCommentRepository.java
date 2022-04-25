package com.esgi.modules.comment.infrastructure;

import com.esgi.kernel.NoSuchEntityException;
import com.esgi.modules.comment.domain.Comment;
import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.comment.domain.CommentRepository;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.user.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class SpringDataCommentRepository implements CommentRepository {
    @Autowired
    private JpaCommentRepository jpaCommentRepository;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public CommentId nextIdentity() {
        return new CommentId(UUID.randomUUID().toString());
    }

    @Override
    public Comment findById(CommentId id) throws NoSuchEntityException {
        return jpaCommentRepository.findById(id.getValue())
                .map(commentMapper::toModel)
                .orElseThrow(() -> NoSuchEntityException.withId(id));
    }

    @Override
    public void add(Comment entity) {
        jpaCommentRepository.save(commentMapper.toEntity(entity));
    }

    @Override
    public void delete(CommentId id) {
        jpaCommentRepository.deleteById(id.getValue());
    }

    @Override
    public List<Comment> findAll() {
        return jpaCommentRepository.findAll().stream().map(commentMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public List<Comment> findByPostId(PostId postId) {
        return jpaCommentRepository
                .findByPostId(postId.getValue())
                .stream().map(commentMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Comment> findByUserId(UserId userId) {
        return jpaCommentRepository
                .findByUserId(userId.getValue())
                .stream().map(commentMapper::toModel)
                .collect(Collectors.toList());
    }
}

@Repository
interface JpaCommentRepository extends JpaRepository<CommentEntity, String> {
    List<CommentEntity> findByPostId(String id);
    List<CommentEntity> findByUserId(String id);
}
