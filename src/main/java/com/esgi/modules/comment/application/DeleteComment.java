package com.esgi.modules.comment.application;

import com.esgi.kernel.Command;
import com.esgi.modules.user.domain.UserId;

public record DeleteComment(String commentId, UserId userId) implements Command {
}
