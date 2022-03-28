package com.esgi.modules.code.application;

import com.esgi.kernel.EventListener;

final public class RunCodeEventListener implements EventListener<RunCodeEvent> {
    @Override
    public void listenTo(RunCodeEvent event) {
        System.out.println("listening to run code event");
    }
}
