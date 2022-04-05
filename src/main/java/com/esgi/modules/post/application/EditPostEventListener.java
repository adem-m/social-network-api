package com.esgi.modules.post.application;

import com.esgi.kernel.EventListener;

public class EditPostEventListener implements EventListener<EditPostEvent> {
    @Override
    public void listenTo(EditPostEvent event) {
        System.out.println("listening EditPostEvent.");
    }
}
