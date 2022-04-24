package com.esgi.modules.follow.application;

import com.esgi.kernel.EventListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateFollowEventListener implements EventListener<CreateFollowEvent> {
    @Override
    public void listenTo(CreateFollowEvent event) {
        log.info("listening CreateFollowEvent.");
    }
}
