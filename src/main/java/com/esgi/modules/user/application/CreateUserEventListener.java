package com.esgi.modules.user.application;

import com.esgi.kernel.EventListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateUserEventListener implements EventListener<CreateUserEvent> {
    @Override
    public void listenTo(CreateUserEvent event) {
        log.info("Listening CreateUserEvent.");
    }
}