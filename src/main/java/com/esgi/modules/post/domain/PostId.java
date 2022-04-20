package com.esgi.modules.post.domain;

import com.esgi.kernel.ValueObjectID;

import java.util.Objects;

public final class PostId implements ValueObjectID {
    private final String value;

    public PostId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostId postId = (PostId) o;
        return value == postId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "PostId{" +
                "value=" + value +
                '}';
    }
}
