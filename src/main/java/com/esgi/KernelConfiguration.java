package com.esgi;

import com.esgi.kernel.*;
import com.esgi.modules.code.application.RunCodeEvent;
import com.esgi.modules.comment.application.CreateCommentEvent;
import com.esgi.modules.comment.application.LikeCommentEvent;
import com.esgi.modules.file.application.CreateFileEvent;
import com.esgi.modules.follow.application.CreateFollowEvent;
import com.esgi.modules.follow.application.UnfollowEvent;
import com.esgi.modules.infrastructure.DefaultEventDispatcher;
import com.esgi.modules.post.application.CreatePostEvent;
import com.esgi.modules.post.application.LikePostEvent;
import com.esgi.modules.user.application.CreateUserEvent;
import com.esgi.modules.user.application.DeleteUserEvent;
import com.esgi.modules.user.application.UpdateUserEvent;
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
        dispatcher.registerEvent(CreateUserEvent.class);
        dispatcher.registerEvent(UpdateUserEvent.class);
        dispatcher.registerEvent(DeleteUserEvent.class);
        dispatcher.registerEvent(CreateFollowEvent.class);
        dispatcher.registerEvent(UnfollowEvent.class);
        dispatcher.registerEvent(CreatePostEvent.class);
        dispatcher.registerEvent(CreateCommentEvent.class);
        dispatcher.registerEvent(LikePostEvent.class);
        dispatcher.registerEvent(LikeCommentEvent.class);
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
