package com.esgi.modules.authentication.application;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.authentication.domain.FullToken;
import com.esgi.modules.authentication.domain.Token;
import com.esgi.modules.security.application.EncryptPasswordCommand;
import com.esgi.modules.user.application.RetrieveUserByEmail;
import com.esgi.modules.user.domain.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record LoginCommandHandler(CommandBus commandBus,
                                  QueryBus queryBus) implements CommandHandler<LoginCommand, FullToken> {

    @Override
    public FullToken handle(LoginCommand command) {
        User user = (User) queryBus.send(new RetrieveUserByEmail(command.email().value()));
        String encryptedPassword = (String) commandBus.send(new EncryptPasswordCommand(command.password().value()));
        if (!user.getPassword().equals(encryptedPassword)) {
            throw new WrongPasswordException();
        }
        log.info("User {} logged in", user.getEmail().getEmail());
        Token token = (Token) commandBus.send(new CreateTokenCommand(user.getEmail().getEmail(), user.getId()));
        return new FullToken(token.value(), user.getId().getValue());
    }
}
