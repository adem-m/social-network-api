package com.esgi.modules.authentication.application;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.authentication.domain.Token;
import com.esgi.modules.user.application.RetrieveUserByEmail;
import com.esgi.modules.user.domain.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record LoginCommandHandler(CommandBus commandBus,
                                  QueryBus queryBus) implements CommandHandler<LoginCommand, Token> {

    @Override
    public Token handle(LoginCommand command) {
        User user = (User) queryBus.send(new RetrieveUserByEmail(command.email().value()));
        if (!user.getPassword().equals(command.password().value())) {
            throw new WrongPasswordException();
        }
        log.info("User {} logged in", user.getEmail().getEmail());
        return (Token) commandBus.send(new CreateTokenCommand(user.getEmail().getEmail(), user.getId()));
    }
}
