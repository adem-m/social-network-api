package com.esgi.modules.follow.domain;

import com.esgi.kernel.ValueObjectID;

import java.util.Objects;

public final class FollowId implements ValueObjectID {
    private final String value;

    public FollowId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowId followId = (FollowId) o;
        return value == followId.value;
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
