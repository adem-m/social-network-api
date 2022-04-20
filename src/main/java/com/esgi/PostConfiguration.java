package com.esgi;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.EventDispatcher;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.post.infrastructure.InMemoryPostRepository;
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
    public CommandBus createPostCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(CreatePost.class, createPostCommandHandler());
        return commandBus;
    }

    @Bean
    public EditPostEventListener editPostEventListener() {
        EventDispatcher dispatcher = this.kernelConfiguration.eventDispatcher();
        EditPostEventListener listener = new EditPostEventListener();
        dispatcher.addListener(EditPostEvent.class, listener);
        return listener;
    }

    @Bean
    public EditPostCommandHandler editPostCommandHandler() {
        return new EditPostCommandHandler(postRepository(), kernelConfiguration.eventDispatcher());
    }

    @Bean
    public CommandBus postCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(EditPost.class, editPostCommandHandler());
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
        queryBus.addHandler(RetrievePostById.class, new RetrievePostByIdHandler(postRepository()));
        return queryBus;
    }

    @Bean
    public RetrievePostByIdHandler retrievePostHandler() {
        return new RetrievePostByIdHandler(postRepository());
    }

    @Bean
    public QueryBus postsQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(RetrievePostsByUserId.class, new RetrievePostsByUserIdHandler(postRepository()));
        return queryBus;
    }

    @Bean
    public RetrievePostsByUserIdHandler retrievePostsHandler() {
        return new RetrievePostsByUserIdHandler(postRepository());
    }
}
