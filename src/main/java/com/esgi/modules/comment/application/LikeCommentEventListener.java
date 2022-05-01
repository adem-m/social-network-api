package com.esgi.modules.comment.application;

import com.esgi.kernel.EventListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LikeCommentEventListener implements EventListener<LikeCommentEvent> {
    @Override
    public void listenTo(LikeCommentEvent event) {
        log.info("listening LikeCommentEvent.");
    }
}
