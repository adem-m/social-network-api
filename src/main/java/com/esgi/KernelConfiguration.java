package com.esgi;

import com.esgi.kernel.*;
import com.esgi.modules.code.application.RunCodeEvent;
import com.esgi.modules.file.application.CreateFileEvent;
import com.esgi.modules.friendship.application.AddFriendshipEvent;
import com.esgi.modules.infrastructure.DefaultEventDispatcher;
import com.esgi.modules.post.application.CreatePostEvent;
import com.esgi.modules.user.application.CreateUserEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KernelConfiguration {
    @Bean
    public EventDispatcher<Event> eventDispatcher() {
        final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listeners = new HashMap<>();
        DefaultEventDispatcher dispatcher = new DefaultEventDispatcher(listeners);
        dispatcher.registerEvent(RunCodeEvent.class);
        dispatcher.registerEvent(CreateFileEvent.class);
        dispatcher.registerEvent(AddFriendshipEvent.class);
        dispatcher.registerEvent(CreatePostEvent.class);
        dispatcher.registerEvent(CreateUserEvent.class);
        return dispatcher;
    }

    @Bean
    public CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> handlers = new HashMap<>();
        return new DefaultCommandBus(handlers);
    }

    @Bean
    public QueryBus queryBus() {
        final Map<Class<? extends Query>, QueryHandler> handlers = new HashMap<>();
        return new DefaultQueryBus(handlers);
    }
}