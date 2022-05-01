package com.esgi.modules.file.application;

import com.esgi.kernel.EventListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
final public class CreateFileEventListener implements EventListener<CreateFileEvent> {
    @Override
    public void listenTo(CreateFileEvent event) {
        log.info("listening to CreateFileEvent");
    }
}
