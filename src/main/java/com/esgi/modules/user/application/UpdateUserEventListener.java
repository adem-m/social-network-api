package com.esgi.modules.user.application;

import com.esgi.kernel.EventListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateUserEventListener implements EventListener<UpdateUserEvent> {
    @Override
    public void listenTo(UpdateUserEvent event) {
        log.info("listening UpdateUserEvent.");
    }
}
