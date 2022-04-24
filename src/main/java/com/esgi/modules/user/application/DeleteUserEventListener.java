package com.esgi.modules.user.application;

import com.esgi.kernel.EventListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteUserEventListener implements EventListener<DeleteUserEvent> {
    @Override
    public void listenTo(DeleteUserEvent event) {
        log.info("listening DeleteUserEvent.");
    }
}
