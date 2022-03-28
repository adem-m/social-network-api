package com.esgi.modules.infrastructure;

import com.esgi.kernel.NoSuchEntityException;
import com.esgi.modules.friendship.domain.Friendship;
import com.esgi.modules.friendship.domain.FriendshipId;
import com.esgi.modules.friendship.domain.FriendshipRepository;
import com.esgi.modules.user.domain.UserId;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryFriendshipRepository implements FriendshipRepository {
    private final AtomicInteger count = new AtomicInteger(0);

    private final Map<FriendshipId, Friendship> data = new ConcurrentHashMap<>();

    @Override
    public FriendshipId nextIdentity() {
        return new FriendshipId(count.incrementAndGet());
    }

    @Override
    public Friendship findById(FriendshipId id) {
        final Friendship friendship = data.get(id);
        if (friendship == null) {
            throw NoSuchEntityException.withId(id);
        }
        return friendship;
    }

    @Override
    public void add(Friendship friendship) {
        data.put(friendship.getFriendshipId(), friendship);
    }

    @Override
    public void delete(FriendshipId id) {
        data.remove(id);
    }

    @Override
    public List<Friendship> findAll() {
        return List.copyOf(data.values());
    }

    @Override
    public boolean existsByFirstUserIdAndSecondUserId(UserId userId1, UserId userId2) {
        return false;
    }

    @Override
    public List<Friendship> findByUserId(UserId userId) {
        return null;
    }
}
