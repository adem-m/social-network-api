package com.esgi.modules.infrastructure;

import com.esgi.kernel.NoSuchEntityException;
import com.esgi.modules.follow.domain.Follow;
import com.esgi.modules.follow.domain.FollowId;
import com.esgi.modules.follow.domain.FollowRepository;
import com.esgi.modules.user.domain.UserId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class InMemoryFollowRepository implements FollowRepository {
    private final AtomicInteger count = new AtomicInteger(0);

    private final Map<FollowId, Follow> data = new ConcurrentHashMap<>();

    @Override
    public FollowId nextIdentity() {
        return new FollowId(count.incrementAndGet());
    }

    @Override
    public Follow findById(FollowId id) {
        final Follow follow = data.get(id);
        if (follow == null) {
            throw NoSuchEntityException.withId(id);
        }
        return follow;
    }

    @Override
    public void add(Follow follow) {
        data.put(follow.getFollowId(), follow);
    }

    @Override
    public void delete(FollowId id) {
        data.remove(id);
    }

    @Override
    public List<Follow> findAll() {
        return List.copyOf(data.values());
    }

    @Override
    public List<Follow> findFollowingByUserId(UserId followerId) {
        List<Follow> allFollow = List.copyOf(data.values());
        List<Follow> result = new ArrayList<>();
        for (Follow follow : allFollow) {
            if (follow.getFollowerId() == followerId)
                result.add(follow);
        }
        return result;
    }

    @Override
    public List<Follow> findFollowersByUserId(UserId followerId) {
        List<Follow> allFollow = List.copyOf(data.values());
        List<Follow> result = new ArrayList<>();
        for (Follow follow : allFollow) {
            if (follow.getFollowedId() == followerId)
                result.add(follow);
        }
        return result;
    }
}