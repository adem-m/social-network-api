package com.esgi.modules.post.application;

import com.esgi.kernel.EventListener;

public class UnlikePostEventListener implements EventListener<UnlikePostEvent> {
    @Override
    public void listenTo(UnlikePostEvent event) {
        System.out.println("listening UnlikePostEvent.");
    }
}
