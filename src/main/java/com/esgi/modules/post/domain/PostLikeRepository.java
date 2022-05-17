package com.esgi.modules.post.domain;

import com.esgi.kernel.Repository;
import com.esgi.modules.user.domain.UserId;

import java.util.List;

public interface PostLikeRepository extends Repository<PostLikeId, PostLike> {
    List<PostLike> findAll();
    List<PostLike> findPostsLikedByUserId(UserId id);
    PostLike findLikeByUserIdAndPostId(UserId userId, PostId postId);
    long countByPostId(PostId postId);
}
