package com.esgi.modules.user.application;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.file.application.DeleteImageCommand;
import com.esgi.modules.user.domain.User;
import com.esgi.modules.user.domain.UserId;
import com.esgi.modules.user.domain.UserRepository;

public final class DeleteUserCommandHandler implements CommandHandler<DeleteUser, UserId> {
    private final UserRepository userRepository;
    private final EventDispatcher<Event> eventEventDispatcher;
    private final CommandBus commandBus;

    public DeleteUserCommandHandler(UserRepository userRepository, EventDispatcher<Event> eventEventDispatcher, CommandBus commandBus) {
        this.userRepository = userRepository;
        this.eventEventDispatcher = eventEventDispatcher;
        this.commandBus = commandBus;
    }

    public UserId handle(DeleteUser deleteUser) {
        final UserId userId = new UserId(deleteUser.userId);
        User user = userRepository.findById(userId);
        if(user.getImage() != null) {
            DeleteImageCommand deleteImage = new DeleteImageCommand(user.getImage());
            commandBus.send(deleteImage);
        }
        userRepository.delete(userId);
        eventEventDispatcher.dispatch(new DeleteUserEvent(userId));
        return userId;
    }
}
