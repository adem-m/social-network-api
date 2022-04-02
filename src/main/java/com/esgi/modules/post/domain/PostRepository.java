package com.esgi.modules.post.domain;

import com.esgi.kernel.Repository;
import com.esgi.modules.user.domain.UserId;

import java.util.List;

public interface PostRepository extends Repository<PostId, Post> {
    List<Post> findAll();

    List<Post> findPostsByUserId(UserId id);
}
