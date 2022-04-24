package com.esgi.modules.post.application;

import com.esgi.kernel.EventListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnlikePostEventListener implements EventListener<UnlikePostEvent> {
    @Override
    public void listenTo(UnlikePostEvent event) {
        log.info("listening UnlikePostEvent.");
    }
}
