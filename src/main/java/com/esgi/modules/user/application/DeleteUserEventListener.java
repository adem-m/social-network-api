package com.esgi.modules.user.application;

import com.esgi.kernel.EventListener;

public class DeleteUserEventListener implements EventListener<DeleteUserEvent> {
    @Override
    public void listenTo(DeleteUserEvent event) {
        System.out.println("listening DeleteUserEvent.");
    }
}
