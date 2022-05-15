package com.esgi.modules.authentication.application;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.authentication.domain.Token;
import com.esgi.modules.user.application.RetrieveUserByEmail;
import com.esgi.modules.user.domain.User;

public record LoginCommandHandler(CommandBus commandBus,
                                  QueryBus queryBus) implements CommandHandler<LoginCommand, Token> {

    @Override
    public Token handle(LoginCommand command) {
        User user = (User) queryBus.send(new RetrieveUserByEmail(command.email().value()));
        if (!user.getPassword().equals(command.password().value())) {
            throw new WrongPasswordException();
        }
        return (Token) commandBus.send(new CreateTokenCommand(user.getEmail().getEmail(), user.getId()));
    }
}
