package com.esgi.modules.post.infrastructure;

import com.esgi.kernel.NoSuchEntityException;
import com.esgi.modules.post.domain.Post;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.post.domain.PostRepository;
import com.esgi.modules.user.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class SpringDataPostRepository implements PostRepository {
    @Autowired
    private JpaPostRepository jpaPostRepository;
    @Autowired
    private PostMapper postMapper;

    @Override
    public PostId nextIdentity() {
        return new PostId(UUID.randomUUID().toString());
    }

    @Override
    public Post findById(PostId id) throws NoSuchEntityException {
        return jpaPostRepository.findById(id.getValue())
                .map(postMapper::toModel)
                .orElseThrow(() -> NoSuchEntityException.withId(id));
    }

    @Override
    public void add(Post entity) {
        jpaPostRepository.save(postMapper.toEntity(entity));
    }

    @Override
    public void delete(PostId id) {
        jpaPostRepository.deleteById(id.getValue());
    }

    @Override
    public List<Post> findAll() {
        return jpaPostRepository.findAll().stream().map(postMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public List<Post> findPostsByUserId(UserId id) {
        return jpaPostRepository
                .findAllByCreatorId(id.getValue())
                .stream().map(postMapper::toModel)
                .collect(Collectors.toList());
    }
}

@Repository
interface JpaPostRepository extends JpaRepository<PostEntity, String> {
    List<PostEntity> findAllByCreatorId(String id);
}