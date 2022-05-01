package com.esgi.modules.comment.application;

import com.esgi.kernel.EventListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EditCommentEventListener implements EventListener<EditCommentEvent> {
    @Override
    public void listenTo(EditCommentEvent event) {
        log.info("listening EditCommentEvent.");
    }
}
