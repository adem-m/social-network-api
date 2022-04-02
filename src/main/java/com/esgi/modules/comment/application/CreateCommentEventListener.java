package com.esgi.modules.comment.application;

import com.esgi.kernel.EventListener;

public class CreateCommentEventListener implements EventListener<CreateCommentEvent> {
    @Override
    public void listenTo(CreateCommentEvent event) {
        System.out.println("listening CreateCommentEvent.");
    }
}
