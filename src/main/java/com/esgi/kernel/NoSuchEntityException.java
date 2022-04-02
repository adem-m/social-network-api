package com.esgi.kernel;

import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.follow.domain.FollowId;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.post.domain.PostLikeId;
import com.esgi.modules.user.domain.UserId;

public final class NoSuchEntityException extends RuntimeException {

    public NoSuchEntityException(String message) {
        super(message);
    }

    public static NoSuchEntityException withId(UserId id) {
        return new NoSuchEntityException(String.format("No Member found with ID %d.", id.getValue()));
    }

    public static NoSuchEntityException withId(PostId id) {
        return new NoSuchEntityException(String.format("No Post found with ID %d.", id.getValue()));
    }

    public static NoSuchEntityException withId(PostLikeId id) {
        return new NoSuchEntityException(String.format("No PostLike found with ID %d.", id.getValue()));
    }

    public static NoSuchEntityException withId(FollowId id) {
        return new NoSuchEntityException(String.format("No Follow found with ID %d.", id.getValue()));
    }

    public static NoSuchEntityException withId(CommentId id) {
        return new NoSuchEntityException(String.format("No Comment found with ID %d.", id.getValue()));
    }
}
