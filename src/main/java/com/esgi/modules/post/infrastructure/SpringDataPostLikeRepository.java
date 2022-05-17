package com.esgi.modules.post.infrastructure;

import com.esgi.kernel.NoSuchEntityException;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.post.domain.PostLike;
import com.esgi.modules.post.domain.PostLikeId;
import com.esgi.modules.post.domain.PostLikeRepository;
import com.esgi.modules.user.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class SpringDataPostLikeRepository implements PostLikeRepository {
    @Autowired
    private JpaPostLikeRepository jpaPostLikeRepository;
    @Autowired
    private PostLikeMapper postLikeMapper;

    @Override
    public PostLikeId nextIdentity() {
        return new PostLikeId(UUID.randomUUID().toString());
    }

    @Override
    public PostLike findById(PostLikeId id) throws NoSuchEntityException {
        return jpaPostLikeRepository.findById(id.getValue())
                .map(postLikeMapper::toModel)
                .orElseThrow(() -> NoSuchEntityException.withId(id));
    }

    @Override
    public void add(PostLike entity) {
        jpaPostLikeRepository.save(postLikeMapper.toEntity(entity));
    }

    @Override
    public void delete(PostLikeId id) {
        jpaPostLikeRepository.deleteById(id.getValue());
    }

    @Override
    public List<PostLike> findAll() {
        return jpaPostLikeRepository.findAll().stream().map(postLikeMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public List<PostLike> findPostsLikedByUserId(UserId id) {
        return jpaPostLikeRepository.findAllByUserId(id.getValue())
                .stream().map(postLikeMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public PostLike findLikeByUserIdAndPostId(UserId userId, PostId postId) {
        PostLikeEntity postLikeEntity = jpaPostLikeRepository.findByUserIdAndPostId(userId.getValue(), postId.getValue());
        return postLikeEntity == null ? null : postLikeMapper.toModel(postLikeEntity);
    }

    @Override
    public long countByPostId(PostId postId) {
        return jpaPostLikeRepository.countAllByPostId(postId.getValue());
    }

    @Override
    public boolean isLikedByUser(UserId userId, PostId postId) {
        return jpaPostLikeRepository.existsByUserIdAndPostId(userId.getValue(), postId.getValue());
    }
}

@Repository
interface JpaPostLikeRepository extends JpaRepository<PostLikeEntity, String> {
    List<PostLikeEntity> findAllByUserId(String id);

    PostLikeEntity findByUserIdAndPostId(String userId, String postId);

    long countAllByPostId(String postId);

    boolean existsByUserIdAndPostId(String userId, String postId);
}
