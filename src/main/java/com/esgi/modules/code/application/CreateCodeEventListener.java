package com.esgi.modules.code.application;

import com.esgi.kernel.EventListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateCodeEventListener implements EventListener<CreateCodeEvent> {
    @Override
    public void listenTo(CreateCodeEvent event) {
        log.info("listening CreateCodeEvent.");
    }
}
