package com.esgi.modules.friendship.application;

import com.esgi.kernel.Command;
import com.esgi.modules.user.domain.UserId;

/**
 * Command object
 */
@SuppressWarnings("all")
public final class AddFriendship implements Command {
    public UserId userId1;
    public UserId userId2;

    public AddFriendship(UserId userId1, UserId userId2){
        this.userId1 = userId1;
        this.userId2 = userId2;
    }
}
