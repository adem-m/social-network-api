package com.esgi.modules.post.domain;

import com.esgi.kernel.ValueObjectID;

import java.util.Objects;

public final class PostLikeId implements ValueObjectID {
    private final String value;

    public PostLikeId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostLikeId postLikeId = (PostLikeId) o;
        return value == postLikeId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "PostLikeId{" +
                "value='" + value + '\'' +
                '}';
    }
}
