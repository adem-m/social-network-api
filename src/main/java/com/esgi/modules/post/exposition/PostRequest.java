package com.esgi.modules.post.exposition;

import com.esgi.modules.user.domain.UserId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class PostRequest {
    @NotNull
    @NotBlank
    public String content;

    @NotNull
    public UserId userId;
}
