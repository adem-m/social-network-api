package com.esgi.modules.infrastructure;

import com.esgi.kernel.NoSuchEntityException;
import com.esgi.modules.user.domain.Email;
import com.esgi.modules.user.domain.User;
import com.esgi.modules.user.domain.UserId;
import com.esgi.modules.user.domain.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public final class InMemoryUserRepository implements UserRepository {
    private final AtomicInteger count = new AtomicInteger(0);

    private final Map<UserId, User> data = new ConcurrentHashMap<>();

    @Override
    public UserId nextIdentity() {
        return new UserId(count.incrementAndGet());
    }

    @Override
    public User findById(UserId id) {
        final User user = data.get(id);
        if (user == null) {
            throw NoSuchEntityException.withId(id);
        }
        return user;
    }

    @Override
    public void add(User user) {
        data.put(user.getId(), user);
    }

    @Override
    public void delete(UserId id) {
        data.remove(id);
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(data.values());
    }

    @Override
    public List<User> findByName(String name) {
        return List.copyOf(data.values().stream()
                .filter(user -> (user.getFirstname() + " " + user.getLastname()).equals(name)).collect(Collectors.toList()));
    }

    @Override
    public User findByEmail(String email){
        if (data.values().stream().noneMatch(user -> user.getEmail().getEmail().equals(email))){
            return null;
        }
        return data.values().stream()
                .filter(user -> user.getEmail().getEmail().equals(email)).collect(Collectors.toList()).get(0);
    }
}
