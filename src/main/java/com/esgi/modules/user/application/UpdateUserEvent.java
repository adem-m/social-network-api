package com.esgi.modules.user.application;

import com.esgi.kernel.ApplicationEvent;
import com.esgi.modules.user.domain.UserId;

public class UpdateUserEvent implements ApplicationEvent {
    private final UserId userId;

    public UpdateUserEvent(UserId userId) {
        this.userId = userId;
    }
}
