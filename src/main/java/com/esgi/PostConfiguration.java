package com.esgi;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.EventDispatcher;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.post.application.*;
import com.esgi.modules.post.domain.PostRepository;
import com.esgi.modules.post.infrastructure.SpringDataPostRepository;
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
        return new SpringDataPostRepository();
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
        return new CreatePostCommandHandler(postRepository(),
                kernelConfiguration.eventDispatcher(),
                kernelConfiguration.commandBus());
    }

    @Bean
    public CommandBus createPostCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(CreatePost.class, createPostCommandHandler());
        return commandBus;
    }

    @Bean
    public DeletePostEventListener deletePostEventListener() {
        EventDispatcher dispatcher = this.kernelConfiguration.eventDispatcher();
        DeletePostEventListener listener = new DeletePostEventListener();
        dispatcher.addListener(DeletePostEvent.class, listener);
        return listener;
    }

    @Bean
    public DeletePostCommandHandler deletePostCommandHandler() {
        return new DeletePostCommandHandler(postRepository(), kernelConfiguration.eventDispatcher());
    }

    @Bean
    public CommandBus deletePostCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(DeletePost.class, deletePostCommandHandler());
        return commandBus;
    }

    @Bean
    public QueryBus postQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(RetrievePostById.class, new RetrievePostByIdHandler(postRepository(), queryBus));
        return queryBus;
    }

    @Bean
    public RetrievePostByIdHandler retrievePostHandler() {
        return new RetrievePostByIdHandler(postRepository(), postQueryBus());
    }

    @Bean
    public QueryBus postsQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(RetrievePostsByUserId.class, new RetrievePostsByUserIdHandler(postRepository(), queryBus));
        return queryBus;
    }

    @Bean
    public RetrievePostsByUserIdHandler retrievePostsHandler() {
        return new RetrievePostsByUserIdHandler(postRepository(), postQueryBus());
    }

    @Bean
    public QueryBus feedQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(RetrieveFeedByUserId.class, new RetrieveFeedByUserIdHandler(postRepository(), queryBus));
        return queryBus;
    }

    @Bean
    public RetrieveFeedByUserIdHandler retrieveFeedByUserIdHandler() {
        return new RetrieveFeedByUserIdHandler(postRepository(), feedQueryBus());
    }
}
