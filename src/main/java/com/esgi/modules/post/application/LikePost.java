package com.esgi.modules.post.application;

import com.esgi.kernel.Command;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.user.domain.UserId;

/**
 * Command object
 */
@SuppressWarnings("all")
public final class LikePost implements Command {
    public final UserId userId;
    public final PostId postId;

    public LikePost(UserId userId, PostId postId){
        this.userId = userId;
        this.postId = postId;
    }
}
