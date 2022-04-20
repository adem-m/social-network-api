package com.esgi.modules.post.exposition;

import com.esgi.modules.code.exposition.CodeRequest;

import javax.validation.constraints.NotNull;

public class PostRequest {
    public String content;

    public CodeRequest code;

    @NotNull
    public String userId;
}
