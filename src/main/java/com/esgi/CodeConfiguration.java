package com.esgi;

import com.esgi.kernel.*;
import com.esgi.modules.code.application.RunCode;
import com.esgi.modules.code.application.RunCodeCommandHandler;
import com.esgi.modules.code.application.RunCodeEvent;
import com.esgi.modules.code.application.RunCodeEventListener;
import com.esgi.modules.infrastructure.DefaultEventDispatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class CodeConfiguration {
    @Bean
    public EventDispatcher<Event> runCodeEventDispatcher() {
        final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listenerMap = new HashMap<>();
        listenerMap.put(RunCodeEvent.class, List.of(new RunCodeEventListener()));
        return new DefaultEventDispatcher(listenerMap);
    }

    @Bean
    public RunCodeCommandHandler runCodeCommandHandler() {
        return new RunCodeCommandHandler(runCodeEventDispatcher());
    }

    @Bean
    public CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap =
                Collections.singletonMap(RunCode.class, runCodeCommandHandler());
        return new SimpleCommandBus(commandHandlerMap);
    }
}
