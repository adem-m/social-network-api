package com.esgi.modules.authentication.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.modules.authentication.domain.TokenService;
import com.esgi.modules.user.domain.UserId;

public record DecodeTokenCommandHandler(
        TokenService tokenService) implements CommandHandler<DecodeTokenCommand, UserId> {
    public UserId handle(DecodeTokenCommand command) {
        if (command.token().value() == null || command.token().value().equals("")) {
            throw new NoTokenProvidedException();
        }
        String userId = tokenService.getUserId(command.token());
        return new UserId(userId);
    }
}
