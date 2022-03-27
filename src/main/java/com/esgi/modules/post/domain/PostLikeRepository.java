package com.esgi.modules.post.domain;

import com.esgi.kernel.Repository;
import com.esgi.modules.user.domain.UserId;

import java.util.List;

public interface PostLikeRepository extends Repository<UserId, PostId> {
    List<PostLike> findAll();
}
