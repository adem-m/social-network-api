package com.esgi.modules.follow.application;

import com.esgi.kernel.EventListener;

public class UnfollowEventListener implements EventListener<UnfollowEvent> {
    @Override
    public void listenTo(UnfollowEvent event) {
        System.out.println("listening UnfollowEvent.");
    }
}
