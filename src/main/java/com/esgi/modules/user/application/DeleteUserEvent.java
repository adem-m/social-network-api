package com.esgi.modules.user.application;

import com.esgi.kernel.ApplicationEvent;
import com.esgi.modules.user.domain.UserId;

public class DeleteUserEvent implements ApplicationEvent {
    private final UserId userId;

    public DeleteUserEvent(UserId userId) {
        this.userId = userId;
    }
}
