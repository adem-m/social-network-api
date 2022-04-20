package com.esgi.modules.user.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.user.domain.UserId;
import com.esgi.modules.user.domain.UserRepository;

public final class UpdateUserCommandHandler implements CommandHandler<UpdateUser, UserId> {
    private final UserRepository userRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public UpdateUserCommandHandler(UserRepository userRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.userRepository = userRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    public UserId handle(UpdateUser updateUser) {
        var userId = new UserId(updateUser.userId);
        var user = userRepository.findById(userId);
        if(!user.getEmail().getEmail().equals(updateUser.email.getEmail()) && !updateUser.email.getEmail().equals(""))
            user.changeEmail(updateUser.email);
        if(!user.getPassword().equals(updateUser.password) && !updateUser.password.equals(""))
            user.changePassword(updateUser.password);
        userRepository.add(user);
        eventEventDispatcher.dispatch(new UpdateUserEvent(userId));
        return userId;
    }
}
