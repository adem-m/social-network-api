package com.esgi.modules.comment.application;

import com.esgi.kernel.EventListener;

public class LikeCommentEventListener implements EventListener<LikeCommentEvent> {
    @Override
    public void listenTo(LikeCommentEvent event) {
        System.out.println("listening LikeCommentEvent.");
    }
}
