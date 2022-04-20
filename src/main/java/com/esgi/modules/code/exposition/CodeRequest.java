package com.esgi.modules.code.exposition;

import com.esgi.modules.codeCompiler.domain.Language;
import com.esgi.modules.post.domain.PostId;

import javax.validation.constraints.NotNull;

public class CodeRequest {
    @NotNull
    public PostId postId;

    @NotNull
    public String source;

    @NotNull
    public Language language;
}
