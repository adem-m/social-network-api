package com.esgi.modules.post.application;

import com.esgi.kernel.EventListener;

public class LikePostEventListener implements EventListener<LikePostEvent> {
    @Override
    public void listenTo(LikePostEvent event) {
        System.out.println("listening LikePostEvent.");
    }
}
