package com.esgi.modules.comment.application;

import com.esgi.kernel.EventListener;

public class DeleteCommentEventListener implements EventListener<DeleteCommentEvent> {
    @Override
    public void listenTo(DeleteCommentEvent event) {
        System.out.println("listening DeleteCommentEvent.");
    }
}
