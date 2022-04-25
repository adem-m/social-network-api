package com.esgi.modules.comment.domain;

import com.esgi.kernel.ValueObjectID;

import java.util.Objects;

public final class CommentLikeId implements ValueObjectID {
    private final String value;

    public CommentLikeId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentLikeId commentLikeId = (CommentLikeId) o;
        return value == commentLikeId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "CommentLikeId{" +
                "value='" + value + '\'' +
                '}';
    }
}
