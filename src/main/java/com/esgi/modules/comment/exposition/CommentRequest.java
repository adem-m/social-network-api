package com.esgi.modules.comment.exposition;

import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.user.domain.UserId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class CommentRequest {
    @NotNull
    @NotBlank
    public PostId postId;

    @NotNull
    @NotBlank
    public String content;

    @NotNull
    @NotBlank
    public UserId userId;

    @NotNull
    @NotBlank
    public Date date;
}
