package com.esgi.modules.post.application;

import com.esgi.kernel.EventListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EditPostEventListener implements EventListener<EditPostEvent> {
    @Override
    public void listenTo(EditPostEvent event) {
        log.info("listening EditPostEvent.");
    }
}
