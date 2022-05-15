package com.esgi.modules.authentication.application;

import com.esgi.kernel.Command;
import com.esgi.modules.user.domain.UserId;

public record CreateTokenCommand(String email, UserId userId) implements Command {
}
