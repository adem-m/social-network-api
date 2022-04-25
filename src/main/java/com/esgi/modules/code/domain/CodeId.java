package com.esgi.modules.code.domain;

import com.esgi.kernel.ValueObjectID;

import java.util.Objects;

public final class CodeId implements ValueObjectID {
    private final String value;

    public CodeId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeId codeId = (CodeId) o;
        return value == codeId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "CodeId{" +
                "value=" + value +
                '}';
    }
}
