package com.esgi.modules.comment.application;

import com.esgi.kernel.EventListener;

public class UnlikeCommentEventListener implements EventListener<UnlikeCommentEvent> {
    @Override
    public void listenTo(UnlikeCommentEvent event) {
        System.out.println("listening UnlikeCommentEvent.");
    }
}
