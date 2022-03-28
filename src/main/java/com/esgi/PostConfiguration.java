package com.esgi;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.EventDispatcher;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.infrastructure.InMemoryPostRepository;
import com.esgi.modules.post.application.*;
import com.esgi.modules.post.domain.PostRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostConfiguration {
    private final KernelConfiguration kernelConfiguration;

    public PostConfiguration(KernelConfiguration kernelConfiguration) {
        this.kernelConfiguration = kernelConfiguration;
    }

    @Bean
    public PostRepository postRepository() {
        return new InMemoryPostRepository();
    }

    @Bean
    public CreatePostEventListener createPostEventListener() {
        EventDispatcher dispatcher = this.kernelConfiguration.eventDispatcher();
        CreatePostEventListener listener = new CreatePostEventListener();
        dispatcher.addListener(CreatePostEvent.class, listener);
        return listener;
    }

    @Bean
    public CreatePostCommandHandler createPostCommandHandler() {
        return new CreatePostCommandHandler(postRepository(), kernelConfiguration.eventDispatcher());
    }

    @Bean
    public CommandBus postCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(CreatePost.class, createPostCommandHandler());
        return commandBus;
    }

    @Bean
    public QueryBus postQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(RetrievePosts.class, new RetrievePostsHandler(postRepository()));
        return queryBus;
    }

    @Bean
    public RetrievePostsHandler retrievePostsHandler() {
        return new RetrievePostsHandler(postRepository());
    }

}
