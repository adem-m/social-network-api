package com.esgi.modules.post.application;

import com.esgi.kernel.EventListener;

public class DeletePostEventListener implements EventListener<DeletePostEvent> {
    @Override
    public void listenTo(DeletePostEvent event) {
        System.out.println("listening DeletePostEvent.");
    }
}
