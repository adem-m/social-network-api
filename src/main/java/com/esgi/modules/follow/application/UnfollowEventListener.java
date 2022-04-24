package com.esgi.modules.follow.application;

import com.esgi.kernel.EventListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnfollowEventListener implements EventListener<UnfollowEvent> {
    @Override
    public void listenTo(UnfollowEvent event) {
        log.info("listening UnfollowEvent.");
    }
}
