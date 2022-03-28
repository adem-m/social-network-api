package com.esgi;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.file.application.CreateFile;
import com.esgi.modules.file.application.CreateFileCommandHandler;
import com.esgi.modules.file.application.CreateFileEvent;
import com.esgi.modules.file.application.CreateFileEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileConfiguration {
    private final KernelConfiguration kernelConfiguration;

    public FileConfiguration(KernelConfiguration kernelConfiguration) {
        this.kernelConfiguration = kernelConfiguration;
    }

    @Bean
    public CreateFileEventListener createFileEventListener() {
        EventDispatcher dispatcher = this.kernelConfiguration.eventDispatcher();
        CreateFileEventListener listener = new CreateFileEventListener();
        dispatcher.addListener(CreateFileEvent.class, listener);
        return listener;
    }

    @Bean
    public CreateFileCommandHandler createFileCommandHandler() {
        return new CreateFileCommandHandler(kernelConfiguration.eventDispatcher());
    }

    @Bean
    public CommandBus fileCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(CreateFile.class, createFileCommandHandler());
        return commandBus;
    }
}
