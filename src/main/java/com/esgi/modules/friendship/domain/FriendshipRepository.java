package com.esgi.modules.friendship.domain;

import com.esgi.kernel.Repository;
import com.esgi.modules.user.domain.UserId;

import java.util.List;

public interface FriendshipRepository extends Repository<FriendshipId, Friendship> {
    List<Friendship> findAll();
    boolean existsByFirstUserIdAndSecondUserId(UserId userId1,UserId userId2);
    List<Friendship> findByUserId(UserId userId);
}
