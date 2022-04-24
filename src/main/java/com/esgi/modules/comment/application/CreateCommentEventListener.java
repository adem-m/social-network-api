package com.esgi.modules.comment.application;

import com.esgi.kernel.EventListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateCommentEventListener implements EventListener<CreateCommentEvent> {
    @Override
    public void listenTo(CreateCommentEvent event) {
        log.info("listening CreateCommentEvent.");
    }
}
