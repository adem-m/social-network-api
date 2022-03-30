package com.esgi.modules.follow.application;

import com.esgi.kernel.EventListener;

public class CreateFollowEventListener implements EventListener<CreateFollowEvent> {
    @Override
    public void listenTo(CreateFollowEvent event) {
        System.out.println("listening CreateFollowEvent.");
    }
}
