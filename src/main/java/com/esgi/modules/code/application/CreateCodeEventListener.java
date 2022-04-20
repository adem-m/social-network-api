package com.esgi.modules.code.application;

import com.esgi.kernel.EventListener;

public class CreateCodeEventListener implements EventListener<CreateCodeEvent> {
    @Override
    public void listenTo(CreateCodeEvent event) {
        System.out.println("listening CreateCodeEvent.");
    }
}
