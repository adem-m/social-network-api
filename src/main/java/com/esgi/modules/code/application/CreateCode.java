package com.esgi.modules.code.application;

import com.esgi.kernel.Command;
import com.esgi.modules.code.domain.CodeId;
import com.esgi.modules.codeCompiler.domain.Language;
import com.esgi.modules.post.domain.PostId;

import java.util.Objects;

/**
 * Command object
 */
@SuppressWarnings("all")
public final class CreateCode implements Command {
    public final PostId postId;
    public String source;
    public Language language;

    public CreateCode(PostId postId, String source, Language language) {
        this.postId = Objects.requireNonNull(postId);
        this.source = Objects.requireNonNull(source);
        this.language = Objects.requireNonNull(language);
    }
}
