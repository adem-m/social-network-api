package com.esgi;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.EventDispatcher;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.comment.application.*;
import com.esgi.modules.comment.domain.CommentRepository;
import com.esgi.modules.infrastructure.InMemoryCommentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommentConfiguration {
    private final KernelConfiguration kernelConfiguration;

    public CommentConfiguration(KernelConfiguration kernelConfiguration) {
        this.kernelConfiguration = kernelConfiguration;
    }

    @Bean
    public CommentRepository commentRepository() {
        return new InMemoryCommentRepository();
    }

    @Bean
    public CreateCommentEventListener createCommentEventListener() {
        EventDispatcher dispatcher = this.kernelConfiguration.eventDispatcher();
        CreateCommentEventListener listener = new CreateCommentEventListener();
        dispatcher.addListener(CreateCommentEvent.class, listener);
        return listener;
    }

    @Bean
    public CreateCommentCommandHandler createCommentCommandHandler() {
        return new CreateCommentCommandHandler(commentRepository(), kernelConfiguration.eventDispatcher());
    }

    @Bean
    public CommandBus commentCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(CreateComment.class, createCommentCommandHandler());
        return commandBus;
    }

    @Bean
    public QueryBus commentQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(RetrieveComments.class, new RetrieveCommentsHandler(commentRepository()));
        return queryBus;
    }

    @Bean
    public RetrieveCommentsHandler retrieveCommentsHandler() {
        return new RetrieveCommentsHandler(commentRepository());
    }

}
