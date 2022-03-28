package com.esgi.modules.file.application;

import com.esgi.kernel.EventListener;

final public class CreateFileEventListener implements EventListener<CreateFileEvent> {
    @Override
    public void listenTo(CreateFileEvent event) {
        System.out.println("listening to CreateFileEvent");
    }
}
