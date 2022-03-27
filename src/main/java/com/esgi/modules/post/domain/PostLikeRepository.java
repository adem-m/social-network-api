package com.esgi.modules.post.domain;

import com.esgi.kernel.Repository;

import java.util.List;

public interface PostLikeRepository extends Repository<PostLikeId, PostLike> {
    List<PostLike> findAll();
}
