package com.esgi.modules.comment.domain;

import com.esgi.kernel.ValueObjectID;

import java.util.Objects;

public final class CommentId implements ValueObjectID {
    private final String value;

    public CommentId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentId commentId = (CommentId) o;
        return value == commentId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "CommentId{" +
                "value='" + value + '\'' +
                '}';
    }
}
