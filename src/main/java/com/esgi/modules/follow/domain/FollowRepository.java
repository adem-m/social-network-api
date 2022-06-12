package com.esgi.modules.follow.domain;

import com.esgi.kernel.Repository;
import com.esgi.modules.user.domain.UserId;

import java.util.List;

public interface FollowRepository extends Repository<FollowId, Follow> {
    List<Follow> findAll();
    List<Follow> findFollowingByUserId(UserId userId);
    List<Follow> findFollowersByUserId(UserId userId);
    Follow findFollowBetweenTwoUser(UserId followerId, UserId followedId);
    boolean isFollowing(UserId followerId, UserId followedId);
}
