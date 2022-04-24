package com.esgi.modules.post.application;

import com.esgi.kernel.EventListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LikePostEventListener implements EventListener<LikePostEvent> {
    @Override
    public void listenTo(LikePostEvent event) {
        log.info("listening LikePostEvent.");
    }
}
