package com.esgi.kernel;

import com.esgi.modules.post.domain.PostId;
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
}
