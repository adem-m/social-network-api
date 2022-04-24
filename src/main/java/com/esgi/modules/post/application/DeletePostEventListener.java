package com.esgi.modules.post.application;

import com.esgi.kernel.EventListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeletePostEventListener implements EventListener<DeletePostEvent> {
    @Override
    public void listenTo(DeletePostEvent event) {
        log.info("listening DeletePostEvent.");
    }
}
