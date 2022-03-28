package com.esgi;

import com.esgi.kernel.*;
import com.esgi.modules.file.application.CreateFile;
import com.esgi.modules.file.application.CreateFileCommandHandler;
import com.esgi.modules.file.application.CreateFileEvent;
import com.esgi.modules.file.application.CreateFileEventListener;
import com.esgi.modules.infrastructure.DefaultEventDispatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class FileConfiguration {
    @Bean
    public EventDispatcher<Event> createFileEventDispatcher() {
        final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listenerMap = new HashMap<>();
        listenerMap.put(CreateFileEvent.class, List.of(new CreateFileEventListener()));
        return new DefaultEventDispatcher(listenerMap);
    }

    @Bean
    public CreateFileCommandHandler createFileCommandHandler() {
        return new CreateFileCommandHandler(createFileEventDispatcher());
    }

    @Bean
    public CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap =
                Collections.singletonMap(CreateFile.class, createFileCommandHandler());
        return new SimpleCommandBus(commandHandlerMap);
    }
}
