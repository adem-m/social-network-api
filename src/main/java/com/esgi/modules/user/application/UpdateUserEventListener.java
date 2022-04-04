package com.esgi.modules.user.application;

import com.esgi.kernel.EventListener;

public class UpdateUserEventListener implements EventListener<UpdateUserEvent> {
    @Override
    public void listenTo(UpdateUserEvent event) {
        System.out.println("listening UpdateUserEvent.");
    }
}
