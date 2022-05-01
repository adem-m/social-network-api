package com.esgi.modules.codeCompiler.application;

import com.esgi.kernel.EventListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
final public class RunCodeEventListener implements EventListener<RunCodeEvent> {
    @Override
    public void listenTo(RunCodeEvent event) {
        log.info("listening to run code event");
    }
}
