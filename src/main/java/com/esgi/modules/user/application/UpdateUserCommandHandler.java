package com.esgi.modules.user.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.user.domain.UserId;
import com.esgi.modules.user.domain.UserRepository;

public record UpdateUserCommandHandler(
        UserRepository userRepository,
        EventDispatcher<Event> eventEventDispatcher
) implements CommandHandler<UpdateUser, UserId> {

    public UserId handle(UpdateUser updateUser) {
        var userId = new UserId(updateUser.userId);
        var user = userRepository.findById(userId);
        if (updateUser.email.getEmail() != null &&
                !updateUser.email.getEmail().equals("") &&
                !user.getEmail().getEmail().equals(updateUser.email.getEmail())) {
            user.changeEmail(updateUser.email);
        }
        if (updateUser.password != null &&
                !updateUser.password.equals("") &&
                !user.getPassword().equals(updateUser.password)) {
            user.changePassword(updateUser.password);
        }
        userRepository.add(user);
        eventEventDispatcher.dispatch(new UpdateUserEvent(userId));
        return userId;
    }
}
