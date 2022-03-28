package com.esgi.modules.friendship.domain;

import com.esgi.kernel.ValueObjectID;
import com.esgi.modules.post.domain.PostLikeId;

import java.util.Objects;

public final class FriendshipId implements ValueObjectID {
    private final int value;

    public FriendshipId(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendshipId friendshipId = (FriendshipId) o;
        return value == friendshipId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "FriendshipId{" +
                "value=" + value +
                '}';
    }
}
