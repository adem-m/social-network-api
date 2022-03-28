package com.esgi.modules.user.application;

import com.esgi.kernel.ApplicationEvent;
import com.esgi.modules.user.domain.UserId;

public class CreateUserEvent implements ApplicationEvent {
    private final UserId userId;

    public CreateUserEvent(UserId userId) {
        this.userId = userId;
    }
}
