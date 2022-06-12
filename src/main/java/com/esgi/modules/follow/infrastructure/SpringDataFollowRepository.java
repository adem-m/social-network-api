package com.esgi.modules.follow.infrastructure;

import com.esgi.kernel.NoSuchEntityException;
import com.esgi.modules.follow.domain.Follow;
import com.esgi.modules.follow.domain.FollowId;
import com.esgi.modules.follow.domain.FollowRepository;
import com.esgi.modules.user.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class SpringDataFollowRepository implements FollowRepository {
    @Autowired
    private JpaFollowRepository jpaFollowRepository;
    @Autowired
    private FollowMapper followMapper;

    @Override
    public FollowId nextIdentity() {
        return new FollowId(UUID.randomUUID().toString());
    }

    @Override
    public Follow findById(FollowId id) throws NoSuchEntityException {
        return jpaFollowRepository.findById(id.getValue())
                .map(followMapper::toModel)
                .orElseThrow(() -> NoSuchEntityException.withId(id));
    }

    @Override
    public void add(Follow entity) {
        jpaFollowRepository.save(followMapper.toEntity(entity));
    }

    @Override
    public void delete(FollowId id) {
        jpaFollowRepository.deleteById(id.getValue());
    }

    @Override
    public List<Follow> findAll() {
        return jpaFollowRepository.findAll().stream().map(followMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public List<Follow> findFollowingByUserId(UserId followerId) {
        return jpaFollowRepository.findByFollowerId(followerId.getValue())
                .stream().map(followMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Follow> findFollowersByUserId(UserId followedId) {
        return jpaFollowRepository.findByFollowedId(followedId.getValue())
                .stream().map(followMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Follow findFollowBetweenTwoUser(UserId followerId, UserId followedId) {
        FollowEntity followEntity = jpaFollowRepository.findByFollowerIdAndFollowedId(followerId.getValue(), followedId.getValue());
        return followEntity == null ? null : followMapper.toModel(followEntity);
    }

    @Override
    public boolean isFollowing(UserId followerId, UserId followedId) {
        return jpaFollowRepository.existsByFollowerIdAndFollowedId(followerId.getValue(), followedId.getValue());
    }
}

@Repository
interface JpaFollowRepository extends JpaRepository<FollowEntity, String> {
    List<FollowEntity> findByFollowedId(String id);
    List<FollowEntity> findByFollowerId(String id);
    FollowEntity findByFollowerIdAndFollowedId(String followerId, String followedId);
    boolean existsByFollowerIdAndFollowedId(String followerId, String followedId);
}
