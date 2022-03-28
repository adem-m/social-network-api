package com.esgi.modules.post.domain;

import com.esgi.kernel.Repository;

import java.util.List;

public interface PostRepository extends Repository<PostId, Post> {
    List<Post> findAll();
}
