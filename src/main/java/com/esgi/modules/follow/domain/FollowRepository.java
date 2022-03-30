package com.esgi.modules.follow.domain;

import com.esgi.kernel.Repository;
import com.esgi.modules.user.domain.UserId;

import java.util.List;

public interface FollowRepository extends Repository<FollowId, Follow> {
    List<Follow> findAll();
    List<Follow> findByUserId(UserId userId);
}
