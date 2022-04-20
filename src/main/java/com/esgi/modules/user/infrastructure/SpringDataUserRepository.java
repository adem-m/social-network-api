package com.esgi.modules.user.infrastructure;

import com.esgi.kernel.NoSuchEntityException;
import com.esgi.modules.user.domain.User;
import com.esgi.modules.user.domain.UserId;
import com.esgi.modules.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class SpringDataUserRepository implements UserRepository {
    @Autowired
    private JpaUserRepository jpaUserRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserId nextIdentity() {
        return new UserId(UUID.randomUUID().toString());
    }

    @Override
    public User findById(UserId id) throws NoSuchEntityException {
        return jpaUserRepository.findById(id.getValue())
                .map(userMapper::toModel)
                .orElseThrow(() -> NoSuchEntityException.withId(id));
    }

    @Override
    public void add(User entity) {
        jpaUserRepository.save(userMapper.toEntity(entity));
    }

    @Override
    public void delete(UserId id) {
        jpaUserRepository.delete(
                jpaUserRepository.findById(id.getValue())
                        .orElseThrow(() -> NoSuchEntityException.withId(id)));
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll().stream().map(userMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public List<User> findByName(String name) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        UserEntity userEntity = jpaUserRepository.findByEmail(email);
        return userEntity == null ? null : userMapper.toModel(userEntity);
    }
}

@Repository
interface JpaUserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByEmail(String email);
}