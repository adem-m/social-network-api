package com.esgi.modules.post.application;

import com.esgi.kernel.EventListener;

public class CreatePostEventListener implements EventListener<CreatePostEvent> {
    @Override
    public void listenTo(CreatePostEvent event) {
        System.out.println("listening CreatePostEvent.");
    }
}
