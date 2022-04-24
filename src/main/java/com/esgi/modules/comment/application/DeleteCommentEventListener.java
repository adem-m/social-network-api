package com.esgi.modules.comment.application;

import com.esgi.kernel.EventListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteCommentEventListener implements EventListener<DeleteCommentEvent> {
    @Override
    public void listenTo(DeleteCommentEvent event) {
        log.info("listening DeleteCommentEvent.");
    }
}
