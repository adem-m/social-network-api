package com.esgi;

import com.esgi.kernel.*;
import com.esgi.modules.infrastructure.DefaultEventDispatcher;
import com.esgi.modules.infrastructure.InMemoryPostRepository;
import com.esgi.modules.post.application.*;
import com.esgi.modules.post.domain.PostRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class PostConfiguration {
    @Bean
    public PostRepository postRepository() {
        return new InMemoryPostRepository();
    }

    @Bean
    public EventDispatcher<Event> postEventEventDispatcher() {
        final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listenerMap = new HashMap<>();
        listenerMap.put(CreatePostEvent.class, List.of(new CreatePostEventListener()));
        return new DefaultEventDispatcher(listenerMap);
    }

    @Bean
    public CreatePostCommandHandler createPostCommandHandler() {
        return new CreatePostCommandHandler(postRepository(), postEventEventDispatcher());
    }

    @Bean
    public CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap =
                Collections.singletonMap(CreatePost.class, new CreatePostCommandHandler(postRepository(), postEventEventDispatcher()));
        return new SimpleCommandBus(commandHandlerMap);
    }

    @Bean
    public QueryBus queryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap =
                Collections.singletonMap(RetrievePosts.class, new RetrievePostsHandler(postRepository()));
        return new SimpleQueryBus(queryHandlerMap);
    }

    @Bean
    public RetrievePostsHandler retrievePostsHandler() {
        return new RetrievePostsHandler(postRepository());
    }

}
