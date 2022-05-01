package com.esgi.modules.comment.application;

import com.esgi.kernel.EventListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnlikeCommentEventListener implements EventListener<UnlikeCommentEvent> {
    @Override
    public void listenTo(UnlikeCommentEvent event) {
        log.info("listening UnlikeCommentEvent.");
    }
}
