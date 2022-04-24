package com.esgi.modules.code.domain;

import com.esgi.kernel.Entity;
import com.esgi.modules.post.domain.PostId;

import java.util.Objects;

public final class Code implements Entity<CodeId> {
    private final CodeId codeId;
    private final PostId postId;
    private String source;
    private final String language;

    public Code(CodeId codeId, PostId postId, String source, String language){
        this.codeId = Objects.requireNonNull(codeId);
        this.postId = Objects.requireNonNull(postId);
        this.source = Objects.requireNonNull(source);
        this.language = Objects.requireNonNull(language);
    }

    @Override
    public CodeId id() {
        return codeId;
    }

    public CodeId getCodeId() {
        return codeId;
    }

    public PostId getPostId() {
        return postId;
    }

    public String getSource() {
        return source;
    }

    public String getLanguage() {
        return language;
    }

    public void changeSource(String newSource) {
        this.source = Objects.requireNonNull(newSource);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Code code = (Code) o;
        return Objects.equals(codeId, code.codeId) && Objects.equals(source, code.source) && Objects.equals(language, code.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeId, source, language);
    }

    @Override
    public String toString() {
        return "Code{" +
                "id=" + codeId +
                ", postId='" + postId + '\'' +
                ", source='" + source + '\'' +
                ", language='" + language +
                '}';
    }
}
