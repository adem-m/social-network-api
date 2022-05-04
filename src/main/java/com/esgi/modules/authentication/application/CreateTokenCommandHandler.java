package com.esgi.modules.authentication.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.modules.authentication.domain.Token;
import com.esgi.kernel.TokenService;

public record CreateTokenCommandHandler(
        TokenService tokenService) implements CommandHandler<CreateTokenCommand, Token> {

    @Override
    public Token handle(CreateTokenCommand command) {
        return tokenService.generateToken(command.email(), command.userId().getValue());
    }
}
