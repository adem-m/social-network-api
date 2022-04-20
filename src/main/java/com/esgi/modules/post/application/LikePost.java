package com.esgi.modules.post.application;

import com.esgi.kernel.Command;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.user.domain.UserId;

/**
 * Command object
 */
@SuppressWarnings("all")
public final class LikePost implements Command {
    public final String userId;
    public final String postId;

    public LikePost(String userId, String postId){
        this.userId = userId;
        this.postId = postId;
    }
}
