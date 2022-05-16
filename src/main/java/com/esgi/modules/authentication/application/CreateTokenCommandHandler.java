package com.esgi.modules.authentication.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.modules.authentication.domain.Token;
import com.esgi.modules.authentication.domain.TokenService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record CreateTokenCommandHandler(
        TokenService tokenService) implements CommandHandler<CreateTokenCommand, Token> {

    @Override
    public Token handle(CreateTokenCommand command) {
        log.info("Token creation");
        return tokenService.generateToken(command.email(), command.userId().getValue());
    }
}
