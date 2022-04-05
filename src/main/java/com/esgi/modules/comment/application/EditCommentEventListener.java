package com.esgi.modules.comment.application;

import com.esgi.kernel.EventListener;

public class EditCommentEventListener implements EventListener<EditCommentEvent> {
    @Override
    public void listenTo(EditCommentEvent event) {
        System.out.println("listening EditCommentEvent.");
    }
}
