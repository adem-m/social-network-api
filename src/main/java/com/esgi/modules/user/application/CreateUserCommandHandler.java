package com.esgi.modules.user.application;

import com.esgi.kernel.*;
import com.esgi.modules.security.application.EncryptPasswordCommand;
import com.esgi.modules.user.domain.Email;
import com.esgi.modules.user.domain.User;
import com.esgi.modules.user.domain.UserId;
import com.esgi.modules.user.domain.UserRepository;

public final class CreateUserCommandHandler implements CommandHandler<CreateUser, UserId> {
    private final UserRepository userRepository;
    private final EventDispatcher<Event> eventEventDispatcher;
    private final CommandBus commandBus;

    public CreateUserCommandHandler(UserRepository userRepository, EventDispatcher<Event> eventEventDispatcher, CommandBus commandBus) {
        this.userRepository = userRepository;
        this.eventEventDispatcher = eventEventDispatcher;
        this.commandBus = commandBus;
    }

    public UserId handle(CreateUser createUser) {
        if (userRepository.findByEmail(createUser.email.getEmail()) == null) {
            final UserId userId = userRepository.nextIdentity();
            String encryptedPassword = (String) commandBus.send(new EncryptPasswordCommand(createUser.password));
            User user = new User(
                    userId,
                    createUser.lastname,
                    createUser.firstname,
                    new Email(createUser.email.getEmail()),
                    encryptedPassword);
            userRepository.add(user);
            eventEventDispatcher.dispatch(new CreateUserEvent(userId));
            return userId;
        }
        throw AlreadyExistsException.withEmail(createUser.email);
    }
}
