package com.esgi.modules.user.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.user.domain.UserId;
import com.esgi.modules.user.domain.UserRepository;

public class DeleteUserCommandHandler implements CommandHandler<DeleteUser, UserId> {
    private final UserRepository userRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public DeleteUserCommandHandler(UserRepository userRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.userRepository = userRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    public UserId handle(DeleteUser deleteUser) {
        final UserId userId = new UserId(deleteUser.userId);
        userRepository.delete(userId);
        eventEventDispatcher.dispatch(new DeleteUserEvent(userId));
        return userId;
    }
}
